package com.zerg.tiamat.model;

import com.huobi.client.model.Candlestick;
import com.huobi.client.model.event.CandlestickEvent;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.Iterator;

/**
 * @author : xuyang
 * @date : 2019-12-30 23:41
 */
public class KLineModel extends AbstractCandlestickModel<Candlestick> {
    private static final String NAME = "KLINE";

    public KLineModel(Integer size){
        super(NAME, size);
    }

    public KLineModel(String name, Integer size){
        super(name, size);
    }

    @Override
    public Candlestick getUpdate(CandlestickEvent candlestickEvent) {
        return candlestickEvent.getData();
    }

    @Override
    public Candlestick getAppend(CandlestickEvent candlestickEvent) {
        return candlestickEvent.getData();
    }


    @Override
    public Candlestick getAverage(Integer days) {
        Deque<Candlestick> candlestickEventDeque = getCandlestickEventDeque();
        Iterator<Candlestick> candlestickIterator = candlestickEventDeque.descendingIterator();
        Integer count = 1;
        Candlestick result = new Candlestick();
        while (candlestickIterator.hasNext() && count <= days){
            Candlestick next = candlestickIterator.next();
            result.setHigh(result.getHigh().add(next.getHigh()));
            result.setLow(result.getLow().add(next.getLow()));
            result.setOpen(result.getOpen().add(next.getOpen()));
            result.setClose(result.getClose().add(next.getClose()));
            result.setCount(result.getCount() + next.getCount());
            result.setAmount(result.getAmount().add(next.getAmount()));
            result.setVolume(result.getVolume().add(next.getVolume()));
        }

        result.setHigh(result.getHigh().divide(BigDecimal.valueOf(count), 3, BigDecimal.ROUND_CEILING));
        result.setLow(result.getLow().divide(BigDecimal.valueOf(count), 3, BigDecimal.ROUND_CEILING));
        result.setOpen(result.getOpen().divide(BigDecimal.valueOf(count), 3, BigDecimal.ROUND_CEILING));
        result.setClose(result.getClose().divide(BigDecimal.valueOf(count), 3, BigDecimal.ROUND_CEILING));
        result.setCount(result.getCount() / count);
        result.setAmount(result.getAmount().divide(BigDecimal.valueOf(count), 3, BigDecimal.ROUND_CEILING));
        result.setVolume(result.getVolume().divide(BigDecimal.valueOf(count), 3, BigDecimal.ROUND_CEILING));

        return result;
    }
}
