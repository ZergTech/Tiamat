package com.zerg.tiamat.common.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zerg.tiamat.common.http.filter.ResponseFilter;
import com.zerg.tiamat.common.http.sign.Signature;

import java.util.Map;
import java.util.Objects;

/**
 * @author : xuyang
 * @date : 2019-09-19 19:57
 */

public abstract class AbstractRestHelper implements HttpRequest {
    private HttpHelper httpHelper = HttpHelper.newInstance();

    public AbstractRestHelper() {
    }

    protected abstract Signature setSignature();

    public Signature getSignature() {
        Signature signature = setSignature();
        if (Objects.isNull(signature)) {
            throw new IllegalArgumentException("签名未设置");
        }
        return signature;
    }

    public String doGet(RequestProperties myRequestProperties) {
        return doGet(myRequestProperties, getSignature());
    }

    public String doGet(RequestProperties myRequestProperties, Signature signature) {
        String url = signature.signatureGet(myRequestProperties);
        return httpHelper.doGet(url);
    }

    ////

    public <T> T deserializeFromGet(RequestProperties myRequestProperties, Class<T> clazz) {
        return deserializeFromGet(myRequestProperties, clazz, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromGet(RequestProperties myRequestProperties, TypeReference<T> type) {
        return deserializeFromGet(myRequestProperties, type, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromGet(RequestProperties myRequestProperties, Class<T> clazz, ResponseFilter responseFilter) {
        String url = getSignature().signatureGet(myRequestProperties);
        return httpHelper.deserializeFromGet(url, clazz, responseFilter);
    }

    public <T> T deserializeFromGet(RequestProperties myRequestProperties, TypeReference<T> type, ResponseFilter responseFilter) {
        String url = getSignature().signatureGet(myRequestProperties);
        return httpHelper.deserializeFromGet(url, type, responseFilter);
    }

    ////

    public <T> T deserializeFromJson(RequestProperties myRequestProperties, Object body, Class<T> clazz) {
        return deserializeFromJson(myRequestProperties, body, clazz, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromJson(RequestProperties myRequestProperties, Object body, TypeReference<T> type) {
        return deserializeFromJson(myRequestProperties, body, type, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromJson(RequestProperties myRequestProperties, Object body, Class<T> clazz, ResponseFilter responseFilter) {
        Object jsonBody = getSignature().signatureJson(myRequestProperties, body);
        return httpHelper.deserializeFromJson(myRequestProperties.getUrl(), jsonBody, clazz, responseFilter);
    }

    public <T> T deserializeFromJson(RequestProperties myRequestProperties, Object body, TypeReference<T> type, ResponseFilter responseFilter) {
        Object jsonBody = getSignature().signatureJson(myRequestProperties, body);
        return httpHelper.deserializeFromJson(myRequestProperties.getUrl(), jsonBody, type, responseFilter);
    }

    ////

    public <T> T deserializeFromForm(RequestProperties myRequestProperties, Map<String, Object> body, Class<T> clazz) {
        return deserializeFromForm(myRequestProperties, body, clazz, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromForm(RequestProperties myRequestProperties, Map<String, Object> body, TypeReference<T> type) {
        return deserializeFromForm(myRequestProperties, body, type, DEFAULT_RESPONSE_FILTER);
    }

    public <T> T deserializeFromForm(RequestProperties myRequestProperties, Map<String, Object> body, Class<T> clazz, ResponseFilter responseFilter) {
        Map<String, Object> formBody = getSignature().signatureForm(myRequestProperties, body);
        return httpHelper.deserializeFromForm(myRequestProperties.getUrl(), formBody, clazz, responseFilter);
    }

    public <T> T deserializeFromForm(RequestProperties myRequestProperties, Map<String, Object> body, TypeReference<T> type, ResponseFilter responseFilter) {
        Map<String, Object> formBody = getSignature().signatureForm(myRequestProperties, body);
        return httpHelper.deserializeFromForm(myRequestProperties.getUrl(), formBody, type, responseFilter);
    }

    ////

    public AbstractRestHelper setHeader(Map<String, String> header) {
        httpHelper.setHeader(header);
        return this;
    }

    public AbstractRestHelper addHeader(String key, String val) {
        httpHelper.addHeader(key, val);
        return this;
    }

    public String getHeader(String key) {
        return httpHelper.getHeader(key);

    }

    public AbstractRestHelper setCookie(String val) {
        httpHelper.setCookie(val);
        return this;
    }

    public AbstractRestHelper addCookie(String val) {
        httpHelper.addCookie(val);
        return this;
    }

    public String getCookie() {
        return httpHelper.getCookie();
    }

    public AbstractRestHelper clearHeader() {
        httpHelper.clearHeader();
        return this;
    }

    public AbstractRestHelper clearCookie() {
        httpHelper.clearCookie();
        return this;
    }

    public static AbstractRestHelper simpleRestHelper() {
        SimpleRestHelper client = new SimpleRestHelper();
        return client.getHelper();
    }

    static class SimpleRestHelper {
        private Signature signature = DEFAULT_SIGN;

        public void sign(Signature signature) {
            this.signature = signature;
        }

        AbstractRestHelper getHelper() {
            return new AbstractRestHelper() {
                @Override
                protected Signature setSignature() {
                    return signature;
                }
            };
        }
    }
}
