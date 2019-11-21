package com.zerg.tiamat.dto;

import java.io.Serializable;


/**
 * @author xuyang
 */


public class PropertyDTO implements Serializable {

    /**
     * 初始资金
     */
    private Double startUp;
    /**
     * 持有货币的均价
     */
    private Double average;
    /**
     * 盈利/亏损金额
     */
    private Double profit;
    /**
     * 持有货币的价值
     */
    private Double currency;
    /**
     * 剩余金额
     */
    private Double wallet;
    /**
     * 已借贷金额
     */
    private Double loan;
    /**
     * 可借贷金额
     */
    private Double bank;

    public PropertyDTO() {
    }

    public Double getStartUp() {
        return startUp;
    }

    public void setStartUp(Double startUp) {
        this.startUp = startUp;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getCurrency() {
        return currency;
    }

    public void setCurrency(Double currency) {
        this.currency = currency;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Double getBank() {
        return bank;
    }

    public void setBank(Double bank) {
        this.bank = bank;
    }


}