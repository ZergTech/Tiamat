package com.zerg.tiamat.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Preconditions;
import com.zerg.tiamat.common.exception.BizException;
import com.zerg.tiamat.common.http.filter.ResponseFilter;
import com.zerg.tiamat.common.http.listener.FailureListener;
import com.zerg.tiamat.common.http.listener.SucceedListener;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : xuyang
 * @date : 2019-09-04 16:28
 */

@Slf4j
public class HttpUtils {
    private HttpUtils() {
    }

    private static final String JSON = "application/json; charset=utf-8";
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS))
            .build();

    //get

    public static String doGet(String url) {
        return doGet(url, Collections.emptyMap());
    }

    public static String doGet(String url, Map<String, String> header) {
        Preconditions.checkArgument(Strings.isNotBlank(url), "url is empty");
        final Request request = buildGetRequest(url, header);
        return doCall(request);
    }


    private static Request buildGetRequest(String url, Map<String, String> header) {
        Headers headers = buildHeader(header);
        return new Request.Builder()
                .url(url)
                .headers(headers)
                .build();
    }

    //post-from

    public static String doPostByForm(String url, Map<String, Object> body) {
        return doPostByForm(url, Collections.emptyMap(), body);
    }


    public static String doPostByForm(String url, Map<String, String> header, Map<String, Object> body) {
        Preconditions.checkArgument(Strings.isNotBlank(url), "url is null");
        final Request request = buildFormRequest(url, header, body);
        return doCall(request);
    }

    private static Request buildFormRequest(String url, Map<String, String> header, Map<String, Object> body) {
        Headers headers = buildHeader(header);
        RequestBody requestBody = buildFormBody(body);
        return buildPostRequest(url, headers, requestBody);
    }

    private static RequestBody buildFormBody(Map<String, Object> body) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        body.forEach((key, val) -> formBuilder.add(key, val.toString()));
        return formBuilder.build();
    }

    //post-json

    public static String doPostByJson(String url, Object body) {
        return doPostByJson(url, Collections.emptyMap(), body);
    }


    public static String doPostByJson(String url, Map<String, String> header, Object body) {
        Preconditions.checkArgument(Strings.isNotBlank(url), "url is null");
        if (Objects.isNull(body)) {
            body = "{}";
        }
        final Request request = buildJsonRequest(url, header, body);
        return doCall(request);
    }

    private static Request buildJsonRequest(String url, Map<String, String> header, Object body) {
        Headers headers = buildHeader(header);
        RequestBody requestBody = buildJsonBody(body);
        return buildPostRequest(url, headers, requestBody);
    }

    private static RequestBody buildJsonBody(Object body) {
        MediaType mediaType = MediaType.parse(JSON);
        String jsonBody = JsonUtils.toJson(body);
        return RequestBody.create(mediaType, jsonBody);
    }

    private static Request buildPostRequest(String url, Headers headers, RequestBody requestBody) {
        return new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();
    }

    private static Headers buildHeader(Map<String, String> head) {
        Headers.Builder headBuilder = new Headers.Builder();
        head.forEach(headBuilder::add);
        return headBuilder.build();
    }

    private static String doCall(Request request) {
        final Call call = OK_HTTP_CLIENT.newCall(request);
        String responseBody = null;
        Response response = null;
        try {
            response = call.execute();
            responseBody = response.body().string();
            log.info("请求url：{}, 返回内容为：{} ", request.url().toString(), responseBody);
        } catch (IOException e) {
            log.info("请求url：{}, 出现网络异常，e:{}", request.url().toString(), e.toString());
            throw new BizException("请求url出现网络异常");
        } finally {
            if (Objects.nonNull(response)) {
                response.close();
            }
        }
        return responseBody;
    }

    ////

    public static void doGetAsync(String url, SucceedListener succeedListener, FailureListener failureListener) {
        doGetAsync(url, Collections.emptyMap(), succeedListener, failureListener);
    }

    public static void doGetAsync(String url, Map<String, String> header, SucceedListener succeedListener, FailureListener failureListener) {
        if (Strings.isBlank(url)) {
            failureListener.call(null, new IOException("url is empty"));
            return;
        }
        final Request request = buildGetRequest(url, header);
        doCallAsync(request, succeedListener, failureListener);
    }

    ////

    public static void doPostByFormAsync(String url, Map<String, Object> body, SucceedListener succeedListener, FailureListener failureListener) {
        doPostByFormAsync(url, Collections.emptyMap(), body, succeedListener, failureListener);
    }

    public static void doPostByFormAsync(String url, Map<String, String> header, Map<String, Object> body, SucceedListener succeedListener, FailureListener failureListener) {
        if (Strings.isBlank(url)) {
            failureListener.call(null, new IOException("url is empty"));
            return;
        }
        final Request request = buildFormRequest(url, header, body);
        doCallAsync(request, succeedListener, failureListener);
    }

    ////

    public static void doPostByJsonAsync(String url, Object body, SucceedListener succeedListener, FailureListener failureListener) {
        doPostByJsonAsync(url, Collections.emptyMap(), body, succeedListener, failureListener);
    }

    public static void doPostByJsonAsync(String url, Map<String, String> header, Object body, SucceedListener succeedListener, FailureListener failureListener) {
        if (Strings.isBlank(url)) {
            failureListener.call(null, new IOException("url is empty"));
            return;
        }
        final Request request = buildJsonRequest(url, header, body);
        doCallAsync(request, succeedListener, failureListener);
    }

    private static void doCallAsync(Request request, SucceedListener succeedListener, FailureListener failureListener) {
        final Call call = OK_HTTP_CLIENT.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                failureListener.call(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                succeedListener.call(call, response);
            }
        });
    }


    ////

    public static <T> T deserializeFromGet(String url, TypeReference<T> type, ResponseFilter filter) {
        return deserializeFromGet(url, Collections.emptyMap(), type, filter);
    }

    public static <T> T deserializeFromGet(String url, Class<T> clazz, ResponseFilter filter) {
        return deserializeFromGet(url, Collections.emptyMap(), clazz, filter);
    }

    public static <T> T deserializeFromGet(String url, Map<String, String> header, TypeReference<T> type, ResponseFilter filter) {
        String response = doGet(url, header);
        response = filter.filter(response);
        return JsonUtils.toBean(response, type);
    }

    public static <T> T deserializeFromGet(String url, Map<String, String> header, Class<T> clazz, ResponseFilter filter) {
        String response = doGet(url, header);
        response = filter.filter(response);
        return JsonUtils.toBean(response, clazz);
    }

    ////

    public static <T> T deserializeFromForm(String url, Map<String, Object> body, TypeReference<T> type, ResponseFilter filter) {
        return deserializeFromForm(url, Collections.emptyMap(), body, type, filter);
    }

    public static <T> T deserializeFromForm(String url, Map<String, Object> body, Class<T> clazz, ResponseFilter filter) {
        return deserializeFromForm(url, Collections.emptyMap(), body, clazz, filter);
    }

    public static <T> T deserializeFromForm(String url, Map<String, String> header, Map<String, Object> body, TypeReference<T> type, ResponseFilter filter) {
        String response = doPostByForm(url, header, body);
        response = filter.filter(response);
        return JsonUtils.toBean(response, type);
    }

    public static <T> T deserializeFromForm(String url, Map<String, String> header, Map<String, Object> body, Class<T> clazz, ResponseFilter filter) {
        String response = doPostByForm(url, header, body);
        response = filter.filter(response);
        return JsonUtils.toBean(response, clazz);
    }

    ////

    public static <T> T deserializeFromJson(String url, Object body, TypeReference<T> type, ResponseFilter filter) {
        return deserializeFromJson(url, Collections.emptyMap(), body, type, filter);
    }

    public static <T> T deserializeFromJson(String url, Object body, Class<T> clazz, ResponseFilter filter) {
        return deserializeFromJson(url, Collections.emptyMap(), body, clazz, filter);
    }


    public static <T> T deserializeFromJson(String url, Map<String, String> header, Object body, TypeReference<T> type, ResponseFilter filter) {
        String response = doPostByJson(url, header, body);
        response = filter.filter(response);
        return JsonUtils.toBean(response, type);
    }

    public static <T> T deserializeFromJson(String url, Map<String, String> header, Object body, Class<T> clazz, ResponseFilter filter) {
        String response = doPostByJson(url, header, body);
        response = filter.filter(response);
        return JsonUtils.toBean(response, clazz);
    }

    ////

    public static String encodeUrl(Object object) {
        String encode;
        try {
            encode = URLEncoder.encode(object.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("object:{}使用url编码失败，e:{}", object, e);
            throw new IllegalArgumentException("使用url编码失败！");
        }
        return encode;
    }

}
