package com.zerg.tiamat.dto;

import java.io.Serializable;


/**
 * @author xuyang
 */

public class OperateDTO implements Serializable {
    private Integer action;
    private Double amount;
    private Double count;
    private Object ext;

    public OperateDTO() {
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "OperateDTO{" +
                "action=" + action +
                ", amount=" + amount +
                ", count=" + count +
                ", ext=" + ext +
                '}';
    }
}