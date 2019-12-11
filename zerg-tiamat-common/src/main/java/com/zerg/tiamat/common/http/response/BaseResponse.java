package com.zerg.tiamat.common.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zerg.tiamat.common.enums.ResultStatusEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> implements Serializable {
    public static final Integer SUCCESS = 0;
    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private boolean isSuccess = false;

    public BaseResponse(ResultStatusEnum responseCodeEnum) {
        this(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
    }

    public BaseResponse(ResultStatusEnum responseCodeEnum, T data) {
        this(responseCodeEnum.getCode(), responseCodeEnum.getMessage(), data);
    }

    public BaseResponse() {
        this(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage());
    }

    public BaseResponse(T data) {
        this(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), data);
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    /****************************** 执行成功 ******************************/

    public static <R> BaseResponse<R> success() {
        BaseResponse<R> res = new BaseResponse<R>(ResultStatusEnum.SUCCESS);
        res.setIsSuccess(true);
        return res;
    }

    public static <R> BaseResponse<R> success(R data) {
        BaseResponse<R> res = new BaseResponse<R>(ResultStatusEnum.SUCCESS, data);
        res.setIsSuccess(true);
        return res;
    }

    public static <R> BaseResponse<R> success(ResultStatusEnum resultStatusEnum) {
        BaseResponse<R> res = new BaseResponse<R>(resultStatusEnum);
        res.setIsSuccess(true);
        return res;
    }

    public static <R> BaseResponse<R> success(ResultStatusEnum resultStatusEnum, R data) {
        BaseResponse<R> res = new BaseResponse<R>(resultStatusEnum, data);
        res.setIsSuccess(true);
        return res;
    }

    /****************************** 执行失败 ******************************/

    public static <R> BaseResponse<R> fail() {
        BaseResponse<R> res = new BaseResponse<R>(ResultStatusEnum.FAILURE);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> BaseResponse<R> fail(R data) {
        BaseResponse<R> res = new BaseResponse<R>(ResultStatusEnum.FAILURE, data);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> BaseResponse<R> fail(ResultStatusEnum resultStatusEnum) {
        BaseResponse<R> res = new BaseResponse<R>(resultStatusEnum);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> BaseResponse<R> fail(ResultStatusEnum resultStatusEnum, R data) {
        BaseResponse<R> res = new BaseResponse<R>(resultStatusEnum, data);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> BaseResponse<R> fail(ResultStatusEnum resultStatusEnum, Integer resultCode) {
        BaseResponse<R> res = new BaseResponse<R>(resultStatusEnum);
        res.setIsSuccess(false);
        res.setCode(resultCode);
        return res;
    }

    public static <R> BaseResponse<R> fail(ResultStatusEnum resultStatusEnum, String resultMsg) {
        BaseResponse<R> res = new BaseResponse<R>(resultStatusEnum);
        res.setIsSuccess(false);
        res.setMessage(resultMsg);
        return res;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setResultStatusEnum(ResultStatusEnum resultStatusEnum) {
        this.setCode(resultStatusEnum.getCode());
        this.setMessage(resultStatusEnum.getMessage());
    }
}
