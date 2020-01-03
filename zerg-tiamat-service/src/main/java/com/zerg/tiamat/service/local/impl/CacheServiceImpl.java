package com.zerg.tiamat.service.local.impl;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.zerg.tiamat.common.utils.MailUtils;
import com.zerg.tiamat.common.utils.Strings;
import com.zerg.tiamat.common.utils.ThreadUtils;
import com.zerg.tiamat.dao.Alarm;
import com.zerg.tiamat.dto.AlarmDTO;
import com.zerg.tiamat.service.dao.AlarmService;
import com.zerg.tiamat.service.local.CacheService;
import com.zerg.tiamat.vo.DataModelVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:12
 */

@Service
public class CacheServiceImpl implements CacheService {

    private static final Cache<String, Alarm> ALARM_CACHE = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build();

    @Resource
    private AlarmService alarmService;

    @Override
    public Collection<DataModelVO> getSystemDataModel() {
        return null;
    }

    @Override
    public Collection<Alarm> getAllAlarm() {
        getIfEmpty();
        return ALARM_CACHE.asMap().values();
    }

    @Override
    public Alarm getUserAlarm(String userEmail) {
        getIfEmpty();
        return ALARM_CACHE.getIfPresent(userEmail);
    }

    @Override
    public void updateAlarm() {
        ALARM_CACHE.invalidateAll();
        pullAlarm();
    }

    private void getIfEmpty() {
        if (ALARM_CACHE.size() == 0){
            pullAlarm();
        }
    }

    private Map<String, Alarm> pullAlarm() {
        Map<String, Alarm> map = alarmService.getAllUser().stream()
                .collect(Collectors.toMap(Alarm::getMailAddress, Function.identity()));
        ALARM_CACHE.putAll(map);
        return map;
    }

    @Override
    public void updateAlarm(AlarmDTO alarmDTO) {
        Preconditions.checkArgument(Objects.nonNull(alarmDTO));
        Preconditions.checkArgument(Strings.isNotBlank(alarmDTO.getEmail()));

        Alarm alarm = buildAlarm(alarmDTO);
        alarmService.updateSelectiveByEmail(alarm);

        updateAlarm();
    }

    private Alarm buildAlarm(AlarmDTO alarmDTO) {
        Alarm alarm = new Alarm();
        alarm.setMailAddress(alarmDTO.getEmail());
        alarm.setHighPrice(alarmDTO.getHigh());
        alarm.setLowPrice(alarmDTO.getLow());
        alarm.setShockRange(alarmDTO.getShocks());
        return alarm;
    }
}
