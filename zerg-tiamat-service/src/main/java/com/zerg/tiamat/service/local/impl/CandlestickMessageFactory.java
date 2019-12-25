package com.zerg.tiamat.service.local.impl;

import com.huobi.client.model.event.CandlestickEvent;
import com.zerg.tiamat.dao.Alarm;
import com.zerg.tiamat.entity.CandlestickMessageModel;
import com.zerg.tiamat.service.local.MessageFactory;
import com.zerg.tiamat.service.local.TemplateMessage;
import org.springframework.stereotype.Service;

/**
 * @author : xuyang
 * @date : 2019-12-24 21:27
 */

@Service
public class CandlestickMessageFactory implements MessageFactory {

    @Override
    public TemplateMessage getMessage(Alarm alarm, CandlestickEvent event) {
        return CandlestickMessageModel.builder()
                .alarm(alarm)
                .currency(event.getSymbol())
                .interval(event.getInterval().toString())
                .high(event.getData().getHigh())
                .low(event.getData().getLow())
                .open(event.getData().getOpen())
                .close(event.getData().getClose())
                .amount(event.getData().getAmount())
                .volume(event.getData().getVolume())
                .build();
    }
}
