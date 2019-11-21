package com.zerg.tiamat.dto;

import java.io.Serializable;

public class ExtraDTO implements Serializable {
    private Object ext;
    private String symbol;
    private Integer period;

    public ExtraDTO() {
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

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }
}