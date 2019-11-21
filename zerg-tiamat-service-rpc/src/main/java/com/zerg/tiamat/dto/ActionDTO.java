package com.zerg.tiamat.dto;

import java.io.Serializable;


/**
 * @author xuyang
 */

public class ActionDTO implements Serializable {
    /**
     * 0:做多 1：做空
     */
    private Integer action;
    /**
     * 做多/做空的金额
     */
    private Double count;
    /**
     * 做多/做空时借贷的金额
     */
    private Double loan;

    public ActionDTO() {
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

}