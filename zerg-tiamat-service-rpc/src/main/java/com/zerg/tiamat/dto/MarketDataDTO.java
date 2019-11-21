package com.zerg.tiamat.dto;

import java.io.Serializable;
import java.util.List;

public class MarketDataDTO implements Serializable {
    private List<CandlestickDTO> list;
    private String symbol;
    private Integer period;

    public MarketDataDTO() {
    }

    public List<CandlestickDTO> getList() {
        return list;
    }

    public void setList(List<CandlestickDTO> list) {
        this.list = list;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}