package com.zerg.tiamat.dto;

import java.io.Serializable;


/**
 * @author xuyang
 */


public class UserInfoDTO implements Serializable {
    private Double hold;
    private Double amount;

    public UserInfoDTO() {
    }

    public Double getHold() {
        return hold;
    }

    public void setHold(Double hold) {
        this.hold = hold;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "hold=" + hold +
                ", amount=" + amount +
                '}';
    }
}