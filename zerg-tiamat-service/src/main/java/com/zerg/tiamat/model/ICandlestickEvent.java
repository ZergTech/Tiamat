package com.zerg.tiamat.model;

import com.huobi.client.SubscriptionListener;
import com.huobi.client.model.event.CandlestickEvent;

import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-12-30 23:00
 */
public interface ICandlestickEvent extends SubscriptionListener<CandlestickEvent> {

    List<ICandlestickModel> getModels();

    CandlestickEvent getLast();
}
