package com.zerg.tiamat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : xuyang
 * @date : 2019-11-22 17:39
 */

@Getter
@AllArgsConstructor
public enum  RequestMethodEnum {

    GET(0, "get"),
    POST(1, "post/json"),
    WEBSOCKET(2, "websocket"),
    DUBBO(3, "dubbo"),

    ;
    private Integer code;
    private String desc;
}
