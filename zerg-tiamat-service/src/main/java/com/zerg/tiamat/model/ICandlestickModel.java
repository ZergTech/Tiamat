package com.zerg.tiamat.model;

import com.huobi.client.model.event.CandlestickEvent;

import java.util.Deque;
import java.util.function.Consumer;

/**
 * @author : xuyang
 * @date : 2019-12-30 23:13
 */
public interface ICandlestickModel<T> extends Consumer<CandlestickEvent> {
    void setName(String name);

    String getName();

    void setSize(Integer size);

    Integer getSize();

    Long getIntervalTimestamp();

    Long getUpdateTimestamp();

    Deque<T> getCandlestickEventDeque();

    T getUpdate(CandlestickEvent candlestickEvent);

    T getAppend(CandlestickEvent candlestickEvent);

    T getLast();

    T getAverage(Integer days);

    /**
     * Performs this operation on the given argument.
     *
     * @param candlestickEvent the input argument
     */
    @Override
    void accept(CandlestickEvent candlestickEvent);
}
