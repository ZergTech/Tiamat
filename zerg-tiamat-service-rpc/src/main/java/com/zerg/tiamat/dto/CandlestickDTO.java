
package com.zerg.tiamat.dto;

import java.io.Serializable;

/**
 * @author xuyang
 */

public class CandlestickDTO implements Serializable {
    private Integer id;
    private Double amount;
    private Integer count;
    private Double open;
    private Double close;
    private Double low;
    private Double high;
    private Double vol;

    public CandlestickDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    @Override
    public String toString() {
        return "CandlestickDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", count=" + count +
                ", open=" + open +
                ", close=" + close +
                ", low=" + low +
                ", high=" + high +
                ", vol=" + vol +
                '}';
    }
}
