package com.zerg.tiamat.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.huobi.client.model.event.CandlestickEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : xuyang
 * @date : 2019-12-27 16:38
 */

@Slf4j
public abstract class AbstractCandlestickModel<T> implements ICandlestickModel<T> {
    private String name;
    private Integer size;
    private volatile Long updateTimestamp;
    private volatile Long intervalTimestamp;
    private Deque<T> candlestickEventDeque;
    private final Lock writeLock = new ReentrantLock();

    public AbstractCandlestickModel(String name){
        this(name, 30);
    }

    public AbstractCandlestickModel(String name, Integer size){
        this.name = name;
        this.size = size;
        updateTimestamp = 0L;
        intervalTimestamp = 0L;
        candlestickEventDeque = Queues.newLinkedBlockingDeque(this.size);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public Long getIntervalTimestamp() {
        return this.intervalTimestamp;
    }

    @Override
    public Long getUpdateTimestamp() {
        return this.updateTimestamp;
    }

    @Override
    public Deque<T> getCandlestickEventDeque() {
        return this.candlestickEventDeque;
    }

    @Override
    public void accept(CandlestickEvent candlestickEvent) {
        Preconditions.checkArgument(Objects.nonNull(candlestickEvent));

        log.info("{}【接收到行情消息】{}", getName(), candlestickEvent);

        Long curTimestamp = candlestickEvent.getTimestamp();
        if (updateTimestamp.compareTo(curTimestamp) > 0) {
            log.warn("{}【接收到行情消息】接收到延时数据{}", getName(), candlestickEvent);
            return;
        }

        Long curIntervalTimestamp = candlestickEvent.getData().getTimestamp();
        if (intervalTimestamp.compareTo(curIntervalTimestamp) > 0) {
            log.warn("{}【接收到行情消息】接收到延时数据{}",  getName(), candlestickEvent);
        } else if (intervalTimestamp.compareTo(curIntervalTimestamp) == 0) {
            writeLock.lock();
            T data = getUpdate(candlestickEvent);
            log.info("{}.getUpdate():{}", getName(), data);
            candlestickEventDeque.removeLast();
            candlestickEventDeque.addLast(data);

            updateTimestamp = candlestickEvent.getTimestamp();
            intervalTimestamp = candlestickEvent.getData().getTimestamp();
            writeLock.unlock();
        } else {
            writeLock.lock();

            if (candlestickEventDeque.size() >= size) {
                candlestickEventDeque.removeFirst();
            }
            T data = getAppend(candlestickEvent);
            log.info("{}.getAppend():{}", getName(), data);
            candlestickEventDeque.addLast(data);

            updateTimestamp = candlestickEvent.getTimestamp();
            intervalTimestamp = candlestickEvent.getData().getTimestamp();

            writeLock.unlock();
        }
    }

    @Override
    public T getLast() {
        return candlestickEventDeque.getLast();
    }


}
