package com.zerg.tiamat.common.http.listener;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author : xuyang
 * @date : 2019-09-04 18:31
 */
public interface SucceedListener {
    void call(Call call, Response response);
}
