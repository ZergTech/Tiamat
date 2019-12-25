package com.zerg.tiamat.service.local.impl;

import com.google.common.collect.Maps;
import com.huobi.client.SubscriptionClient;
import com.huobi.client.model.enums.CandlestickInterval;
import com.huobi.client.model.event.CandlestickEvent;
import com.zerg.tiamat.common.utils.DateTimeUtils;
import com.zerg.tiamat.common.utils.ThreadUtils;
import com.zerg.tiamat.dao.Alarm;
import com.zerg.tiamat.dao.MonitorTask;
import com.zerg.tiamat.service.dao.MonitorTaskService;
import com.zerg.tiamat.service.local.CacheService;
import com.zerg.tiamat.service.local.MessageFactory;
import com.zerg.tiamat.service.local.MonitorService;
import com.zerg.tiamat.service.local.TemplateMessage;
import com.zerg.tiamat.service.rpc.notify.NotifyRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : xuyang
 * @date : 2019-12-19 15:59
 */
@Slf4j
@Service
public class MonitorServiceImpl implements MonitorService {

    private static final Long GLOBAL_MIN_NOTIFY_TIMES = 5 * 60 * 1000L;
    private static volatile Boolean ALARM = true;
    private static final Map<String, Long> LAST_SEND_EMAIL_MAP = Maps.newConcurrentMap();
    private static final SubscriptionClient SUBSCRIPTION_CLIENT = SubscriptionClient.create();

    @Resource
    private CacheService cacheService;
    @Resource
    private MonitorTaskService monitorTaskService;
    @Resource
    private NotifyRpcService notifyRpcService;
    @Resource
    private MessageFactory messageFactory;

    @PostConstruct
    public void init() {
        List<MonitorTask> allTask = monitorTaskService.getAllTask();

        allTask.forEach(task -> {
            Optional<CandlestickInterval> intervalOptional =
                    CandlestickInterval.getEnum(task.getTimeInterval());

            intervalOptional.ifPresent(candlestickInterval -> {
                log.info("订阅任务，task:{}, interval:{}", task, candlestickInterval);
                LAST_SEND_EMAIL_MAP.put(task.getSymbol() + "#" + task.getTimeInterval(), 0L);
                SUBSCRIPTION_CLIENT.subscribeCandlestickEvent(
                        task.getSymbol(),
                        candlestickInterval,
                        this::handleEvent);
            });
        });
    }

    private void handleEvent(CandlestickEvent event) {
        if (!ALARM) {
            return;
        }
        String key = event.getSymbol() + "#" + event.getInterval().toString();
        log.info("【接收到行情数据】key:{}, val:{}", key, event.getData());
        Long lastEmailTime = LAST_SEND_EMAIL_MAP.get(key);
        Long subs = Instant.now().toEpochMilli() - lastEmailTime;
        if (subs <= event.getInterval().getIntervalMillSecond()
                || subs <= GLOBAL_MIN_NOTIFY_TIMES) {
            return;
        }

        BigDecimal open = event.getData().getOpen();
        BigDecimal close = event.getData().getClose();

        BigDecimal sub = null;
        if (close.compareTo(open) > 0) {
            sub = close.subtract(open);
        } else {
            sub = open.subtract(close);
        }
        BigDecimal shocks = sub.divide(open, 3, BigDecimal.ROUND_CEILING);

        ThreadUtils.executeWithoutWait(() -> cacheService.getAllAlarm().parallelStream()
                .forEach(alarm -> {
                    if (checkHighPrice(close, alarm)
                            || checkLowPrice(close, alarm)
                            || checkShcok(shocks, alarm)) {
                        LAST_SEND_EMAIL_MAP.put(key, Instant.ofEpochMilli(event.getTimestamp()).plus(8, ChronoUnit.HOURS).toEpochMilli());
                        TemplateMessage message = messageFactory.getMessage(alarm, event);
                        notifyRpcService.sendMessage(message.getSubject(), message.getMessage());
                    }
                }));
    }

    private boolean checkShcok(BigDecimal shocks, Alarm alarm) {
        return alarm.getShockRange().compareTo(BigDecimal.ZERO) != 0 && shocks.compareTo(alarm.getShockRange()) > 0;
    }

    private boolean checkLowPrice(BigDecimal low, Alarm alarm) {
        return alarm.getLowPrice().compareTo(BigDecimal.ZERO) != 0 && low.compareTo(alarm.getLowPrice()) < 0;
    }

    private boolean checkHighPrice(BigDecimal high, Alarm alarm) {
        return alarm.getHighPrice().compareTo(BigDecimal.ZERO) != 0 && high.compareTo(alarm.getHighPrice()) > 0;
    }

    @Override
    public void startPriceAlarm() {
        ALARM = true;
    }

    @Override
    public void stopPriceAlarm() {
        ALARM = false;
    }
}
