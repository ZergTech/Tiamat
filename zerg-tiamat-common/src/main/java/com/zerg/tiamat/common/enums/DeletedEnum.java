package com.zerg.tiamat.common.enums;

import java.util.Arrays;

public enum DeletedEnum {

    /**
     * 正常
     */
    UNDELETED((byte) 0, "未删除"),
    /**
     * 删除
     */
    DELETED((byte) 1, "已删除");

    private Byte code;
    private String message;

    public static DeletedEnum codeOf(byte code) {
        return Arrays.stream(values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    DeletedEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }

    public Byte getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
