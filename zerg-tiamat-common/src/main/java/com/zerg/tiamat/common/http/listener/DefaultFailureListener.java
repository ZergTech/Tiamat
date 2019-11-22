package com.zerg.tiamat.common.http.listener;

import com.zerg.tiamat.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;


import java.io.IOException;


/**
 * @author : xuyang
 * @date : 2019-09-19 19:33
 */

@Slf4j
public class DefaultFailureListener implements FailureListener {
    @Override
    public void call(Call call, IOException e) {
        log.error("请求：{}, 发生网络异常，异常信息：{}", call.request().toString(), e.toString());
        throw new BizException(e.toString());
    }
}
