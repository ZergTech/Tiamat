package com.zerg.tiamat.common.enums;

import java.util.Arrays;

public enum DeletedEnum {

    /**
     * 正常
     */
    UNDELETED(0, "未删除"),
    /**
     * 删除
     */
    DELETED( 1, "已删除");

    private Integer code;
    private String message;

    public static DeletedEnum codeOf(Integer code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    DeletedEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
