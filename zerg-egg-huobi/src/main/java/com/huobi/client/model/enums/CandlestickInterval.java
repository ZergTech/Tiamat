package com.huobi.client.model.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * 1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year
 */

public enum CandlestickInterval {
    MIN1("1min", 60 * 1000L),
    MIN5("5min", 5 * 60 * 1000L),
    MIN15("15min", 15 * 60 * 1000L),
    MIN30("30min", 30 * 60 * 1000L),
    MIN60("60min", 60 * 60 * 1000L),
    HOUR4("4hour", 4 * 60 * 60 * 1000L),
    DAY1("1day", 24 * 60 * 60 * 1000L),
    MON1("1mon", 30 * 24 * 60 * 60 * 1000L),
    WEEK1("1week", 7 * 24 * 60 * 60 * 1000L),
    YEAR1("1year", 365 * 24 * 60 * 60 * 1000L);

    private final String code;
    private final Long interval;

    CandlestickInterval(String code, Long interval) {
        this.code = code;
        this.interval = interval;
    }

    public static Optional<CandlestickInterval> getEnum(String code) {
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst();
    }


    @Override
    public String toString() {
        return code;
    }


    public Long getIntervalMillSecond(){
        return interval;
    }
}
