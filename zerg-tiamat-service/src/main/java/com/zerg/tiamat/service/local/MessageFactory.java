package com.zerg.tiamat.service.local;

import com.huobi.client.model.event.CandlestickEvent;
import com.zerg.tiamat.dao.Alarm;

/**
 * @author : xuyang
 * @date : 2019-12-24 21:24
 */


public interface MessageFactory {

    TemplateMessage getMessage(Alarm alarm, CandlestickEvent event);
}
