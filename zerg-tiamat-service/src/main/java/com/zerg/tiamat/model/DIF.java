package com.zerg.tiamat.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : xuyang
 * @date : 2019-12-31 00:56
 */

@Data
public class DIF {
    private BigDecimal close;
    private BigDecimal shortDeaSum;
    private BigDecimal longDeaSum;
    private BigDecimal dif;

    public DIF(){
        close = BigDecimal.ZERO;
        shortDeaSum = BigDecimal.ZERO;
        longDeaSum = BigDecimal.ZERO;
        dif = BigDecimal.ZERO;
    }
}
