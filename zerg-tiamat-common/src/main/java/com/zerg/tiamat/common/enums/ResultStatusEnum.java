package com.zerg.tiamat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : xuyang
 * @date : 2019-10-21 13:35
 */

@Getter
@AllArgsConstructor
public enum ResultStatusEnum {
    SUCCESS("成功", 0),
    FAILURE("失败", -1),



    ;
    private String message;
    private Integer code;
}
