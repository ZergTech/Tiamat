package com.zerg.tiamat.common.http.listener;

import com.zerg.tiamat.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author : xuyang
 * @date : 2019-09-19 19:34
 */

@Slf4j
public class DefaultSucceedListener implements SucceedListener {
    @Override
    public void call(Call call, Response response) {
        String body = null;
        try {
            body = response.body().string();
        } catch (IOException e) {
            log.error("请求：{}，读取返回内容失败！错误信息为：{}", call.request().toString(), e.toString());
            throw new BizException(e.toString());
        }
        log.info("请求：{}成功，返回内容为：{}", call.request().toString(), body);
    }
}
