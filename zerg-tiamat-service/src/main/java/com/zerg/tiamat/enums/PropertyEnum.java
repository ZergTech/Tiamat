package com.zerg.tiamat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : xuyang
 * @date : 2019-11-22 17:46
 */

@Getter
@AllArgsConstructor
public enum PropertyEnum {
    NO(0, "发送用户财产信息"),
    YES(1, "不发送用户财产信息"),
    ;

    private Integer code;
    private String desc;
}
