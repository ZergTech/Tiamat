package com.zerg.tiamat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : xuyang
 * @date : 2019-11-22 17:43
 */

@Getter
@AllArgsConstructor
public enum TimeIntervalEnum {

    MIN1(1, "1min"),
    MIN5(2, "5min"),
    MIN15(3, "15min"),
    MIN30(4,"30min"),
    HOUR1(5, "1hour"),
    HOUR4(6, "4hour"),
    DAY1(7, "1day"),
    WEEK1(8, "1week"),
    MONTH1(9, "1month"),
    YEAR1(10, "1year"),

    ;

    private Integer code;
    private String desc;
}
