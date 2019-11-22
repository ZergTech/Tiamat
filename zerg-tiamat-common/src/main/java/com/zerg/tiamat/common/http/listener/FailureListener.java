package com.zerg.tiamat.common.http.listener;

import okhttp3.Call;

import java.io.IOException;

/**
 * @author : xuyang
 * @date : 2019-09-04 18:29
 */
public interface FailureListener {
    void call(Call call, IOException e);
}
