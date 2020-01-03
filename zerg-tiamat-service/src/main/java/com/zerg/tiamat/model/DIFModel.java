package com.zerg.tiamat.model;

import com.huobi.client.model.event.CandlestickEvent;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * @author : xuyang
 * @date : 2019-12-31 00:55
 */
public class DIFModel extends AbstractCandlestickModel<DIF> {
    private static final String NAME = "DIF";
    private Integer shortTime;
    private Integer longTime;

    public DIFModel(Integer shortTime, Integer longTime) {
        super(NAME, 1000);
        this.shortTime = shortTime;
        this.longTime = longTime;
    }

    @Override
    public DIF getUpdate(CandlestickEvent candlestickEvent) {
        DIF dif = new DIF();
        Iterator<DIF> difIterator = getCandlestickEventDeque().descendingIterator();
        int size = getCandlestickEventDeque().size();
        DIF next = difIterator.next();
        dif.setClose(candlestickEvent.getData().getClose());
        dif.setShortDeaSum(next.getShortDeaSum().subtract(next.getClose()).add(candlestickEvent.getData().getClose()));
        dif.setLongDeaSum((next.getLongDeaSum().subtract(next.getClose()).add(candlestickEvent.getData().getClose())));
        dif.setDif(dif.getShortDeaSum().divide(BigDecimal.valueOf(Math.min(size, shortTime)), 3 ,BigDecimal.ROUND_CEILING)
                .subtract(dif.getLongDeaSum().divide(BigDecimal.valueOf(Math.min(size, longTime)), 3 ,BigDecimal.ROUND_CEILING)));
        return dif;
    }

    @Override
    public DIF getAppend(CandlestickEvent candlestickEvent) {
        DIF dif = new DIF();
        int size = getCandlestickEventDeque().size();
        if (size == 0) {
            dif.setClose(candlestickEvent.getData().getClose());
            dif.setShortDeaSum(candlestickEvent.getData().getClose());
            dif.setLongDeaSum(candlestickEvent.getData().getClose());
            dif.setDif(BigDecimal.ZERO);
        }else {
            if (size < shortTime){
                DIF last = getCandlestickEventDeque().getLast();
                dif.setClose(candlestickEvent.getData().getClose());
                dif.setShortDeaSum(last.getShortDeaSum().add(candlestickEvent.getData().getClose()));
                dif.setLongDeaSum(last.getLongDeaSum().add(candlestickEvent.getData().getClose()));
                dif.setDif(BigDecimal.ZERO);
            }else if (size < longTime){
                DIF last = getCandlestickEventDeque().getLast();
                Iterator<DIF> difIterator = getCandlestickEventDeque().descendingIterator();
                DIF head = lastIndex(0, shortTime, difIterator);
                dif.setClose(candlestickEvent.getData().getClose());
                dif.setShortDeaSum(last.getShortDeaSum().add(candlestickEvent.getData().getClose()).subtract(head.getClose()));
                dif.setLongDeaSum(last.getLongDeaSum().add(candlestickEvent.getData().getClose()));
                dif.setDif(dif.getShortDeaSum().divide(BigDecimal.valueOf(shortTime), 3, BigDecimal.ROUND_CEILING)
                        .subtract(dif.getLongDeaSum().divide(BigDecimal.valueOf(size), 3, BigDecimal.ROUND_CEILING)));
            }else {
                DIF last = getCandlestickEventDeque().getLast();
                Iterator<DIF> difIterator = getCandlestickEventDeque().descendingIterator();
                DIF head = lastIndex(0, shortTime, difIterator);
                dif.setClose(candlestickEvent.getData().getClose());
                dif.setShortDeaSum(last.getShortDeaSum().add(candlestickEvent.getData().getClose()).subtract(head.getClose()));
                head = lastIndex(shortTime, longTime, difIterator);
                dif.setLongDeaSum(last.getLongDeaSum().add(candlestickEvent.getData().getClose()).subtract(head.getClose()));
                dif.setDif(dif.getShortDeaSum().divide(BigDecimal.valueOf(shortTime), 3, BigDecimal.ROUND_CEILING)
                        .subtract(dif.getLongDeaSum().divide(BigDecimal.valueOf(longTime), 3, BigDecimal.ROUND_CEILING)));
            }
        }
        return dif;
    }

    private DIF lastIndex(Integer start, Integer end, Iterator<DIF> iterator){
        int count = 0;
        while (count < shortTime - 1){
            iterator.next();
            count++;
        }
        return iterator.next();
    }

    @Override
    public DIF getAverage(Integer days) {
        DIF result = new DIF();
        Integer count = 1;
        Iterator<DIF> difIterator = getCandlestickEventDeque().descendingIterator();
        while (difIterator.hasNext() && count <= days){
            DIF next = difIterator.next();
            result.setDif(result.getDif().add(next.getDif()));
            count++;
        }
        result.setDif(result.getDif().divide(BigDecimal.valueOf(count), 3, BigDecimal.ROUND_CEILING));

        return result;
    }
}
