package com.zerg.tiamat.common.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import com.zerg.tiamat.common.http.filter.ResponseFilter;
import com.zerg.tiamat.common.http.listener.FailureListener;
import com.zerg.tiamat.common.http.listener.SucceedListener;
import com.zerg.tiamat.common.utils.HttpUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Map;

/**
 * @author : xuyang
 * @date : 2019-09-06 16:31
 */


public class HttpHelper implements HttpRequest {
    private static final HttpHelper HTTP_HELPER = new HttpHelper();

    private HttpHelper() {
    }

    private Map<String, String> header = Maps.newConcurrentMap();

    public String doGet(String url) {
        return HttpUtils.doGet(url, header);
    }

    public String doPostByJson(String url, String json) {
        return HttpUtils.doPostByJson(url, header, json);
    }

    public String doPostByForm(String url, Map<String, Object> body) {
        return HttpUtils.doPostByForm(url, header, body);
    }

    public void doGetAsync(String url, SucceedListener succeedListener, FailureListener failureListener) {
        HttpUtils.doGetAsync(url, header, succeedListener, failureListener);
    }

    public void doPostByFormAsync(String url, Map<String, Object> body, SucceedListener succeedListener, FailureListener failureListener) {
        HttpUtils.doPostByFormAsync(url, header, body, succeedListener, failureListener);
    }

    public void doPostByJsonAsync(String url, Object body, SucceedListener succeedListener, FailureListener failureListener) {
        HttpUtils.doPostByJsonAsync(url, header, body, succeedListener, failureListener);
    }

    ////

    public <T> T deserializeFromGet(String url, Class<T> clazz) {
        return HttpUtils.deserializeFromGet(url, header, clazz, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromGet(String url, TypeReference<T> type) {
        return HttpUtils.deserializeFromGet(url, header, type, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromGet(String url, Class<T> clazz, ResponseFilter filter) {
        return HttpUtils.deserializeFromGet(url, header, clazz, filter);
    }

    public <T> T deserializeFromGet(String url, TypeReference<T> type, ResponseFilter filter) {
        return HttpUtils.deserializeFromGet(url, header, type, filter);
    }

    ////

    public <T> T deserializeFromForm(String url, Map<String, Object> body, Class<T> clazz) {
        return HttpUtils.deserializeFromForm(url, header, body, clazz, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromForm(String url, Map<String, Object> body, TypeReference<T> type) {
        return HttpUtils.deserializeFromForm(url, header, body, type, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromForm(String url, Map<String, Object> body, Class<T> clazz, ResponseFilter filter) {
        return HttpUtils.deserializeFromForm(url, header, body, clazz, filter);
    }

    public <T> T deserializeFromForm(String url, Map<String, Object> body, TypeReference<T> type, ResponseFilter filter) {
        return HttpUtils.deserializeFromForm(url, header, body, type, filter);
    }

    ////

    public <T> T deserializeFromJson(String url, Object body, Class<T> clazz) {
        return HttpUtils.deserializeFromJson(url, header, body, clazz, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromJson(String url, Object body, TypeReference<T> type) {
        return HttpUtils.deserializeFromJson(url, header, body, type, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromJson(String url, Object body, Class<T> clazz, ResponseFilter filter) {
        return HttpUtils.deserializeFromJson(url, header, body, clazz, filter);
    }

    public <T> T deserializeFromJson(String url, Object body, TypeReference<T> type, ResponseFilter filter) {
        return HttpUtils.deserializeFromJson(url, header, body, type, filter);
    }


    ////


    public HttpHelper setHeader(Map<String, String> header) {
        this.header = header;
        return this;
    }


    public HttpHelper addHeader(String key, String val) {
        String v1 = header.get(key);
        if (Strings.isBlank(v1)) {
            header.put(key, val);
        } else {
            header.put(key, v1 + ";" + val);
        }
        return this;
    }

    public String getHeader(String key) {
        return header.get(key);
    }


    public HttpHelper setCookie(String val) {
        this.header.put("Cookie", val);
        return this;
    }

    public HttpHelper addCookie(String val) {
        return addHeader("Cookie", val);
    }

    public String getCookie() {
        return getHeader("Cookie");
    }

    public HttpHelper clearHeader() {
        header.clear();
        return this;
    }

    public HttpHelper clearCookie() {
        header.put("Cookie", "");
        return this;
    }

    public static HttpHelper create() {
        return HTTP_HELPER;
    }

    public static HttpHelper newInstance(){
        return new HttpHelper();
    }

}



