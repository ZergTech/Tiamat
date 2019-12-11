package com.zerg.tiamat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : xuyang
 * @date : 2019-11-22 17:41
 */

@Getter
@AllArgsConstructor
public enum MarketEnum {
    DEFAULT(0, ""),
    HUOBI(1, "火币"),
    BIAN(2, "币安"),
    OKEX(3, "OKEx"),

    ;
    private Integer code;
    private String desc;
}
