package com.zerg.tiamat.model;

import com.google.common.collect.Lists;
import com.huobi.client.model.event.CandlestickEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author : xuyang
 * @date : 2019-12-30 22:52
 */


public class RealTimeQuotes implements ICandlestickEvent {
    private CandlestickEvent candlestickEvent;
    private List<ICandlestickModel> models;
    private List<ICandlestickEventListener> listeners;

    public RealTimeQuotes() {
        this.models = Lists.newArrayList(new KLineModel(30), new DIFModel(7, 28));
        this.listeners = Collections.emptyList();
    }

    public RealTimeQuotes(List<ICandlestickModel> models, List<ICandlestickEventListener> listeners) {
        this.models = models;
        this.listeners = listeners;
    }

    @Override
    public List<ICandlestickModel> getModels() {
        return this.models;
    }

    @Override
    public CandlestickEvent getLast() {
        return this.candlestickEvent;
    }

    /**
     * onReceive will be called when get the data sent by server.
     *
     * @param data The data send by server.
     */
    @Override
    public void onReceive(CandlestickEvent data) {
        candlestickEvent = data;
        List<CompletableFuture<Void>> task = models.stream()
                .map(model -> CompletableFuture.runAsync(() -> model.accept(data)))
                .collect(Collectors.toList());
        task.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        listeners.forEach(listener -> listener.accept(this));
    }

}
