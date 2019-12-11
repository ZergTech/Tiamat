package com.zerg.tiamat.advice;

import com.zerg.tiamat.common.exception.BizException;
import com.zerg.tiamat.common.http.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
@ResponseBody
public class ControllerExceptionAdvice {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public BaseResponse illegalArgumentExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.warn("IllegalArgumentException", e);
        return BaseResponse.fail(e.getMessage());
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public BaseResponse illegalStateExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.warn("IllegalStateException", e);
        return BaseResponse.fail(e.getMessage());
    }

    @ExceptionHandler(value = BizException.class)
    public BaseResponse bizExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.warn("BizException", e);
        return BaseResponse.fail(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse baseExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        if (response != null && response.getContentType() != null
                && response.getContentType().contains("vnd.ms-excel")) {
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Content-Disposition", "");
        }
        log.error("未捕获异常=====>", e);
        return BaseResponse.fail();
    }

}
