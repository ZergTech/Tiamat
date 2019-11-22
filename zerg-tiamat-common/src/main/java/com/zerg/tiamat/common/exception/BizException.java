package com.zerg.tiamat.common.exception;


import com.zerg.tiamat.common.enums.ResultStatusEnum;

import java.io.Serializable;


public class BizException extends RuntimeException implements Serializable {
    /** 自定义业务错误代码 */
    private int errorCode;

    public BizException(int errorCode, String message, Throwable e) {
        super(message, e);

        this.errorCode = errorCode;
    }

    public BizException(int errorCode, String message) {
        this(errorCode, message, null);
    }

    public BizException(ResultStatusEnum responseCodeEnum) {
        this(responseCodeEnum.getCode(), responseCodeEnum.getMessage(), null);
    }

    public BizException(ResultStatusEnum responseCodeEnum, String message) {
        this(responseCodeEnum.getCode(), message, null);
    }

    public BizException(int errorCode) {
        this(errorCode, null, null);
    }

    public BizException(int errorCode, Throwable e) {
        this(errorCode, null, e);
    }

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
