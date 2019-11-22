package com.zerg.tiamat.common.http.sign;


import com.zerg.tiamat.common.http.RequestProperties;

import java.util.Map;

/**
 * @author : xuyang
 * @date : 2019-09-19 21:54
 */
public class DefaultSign implements Signature {
    @Override
    public String signatureGet(RequestProperties myRequestProperties) {
        return buildUrl(myRequestProperties.getUrl(), myRequestProperties.getArgs());
    }

    private String buildUrl(String url, Map<String, Object> params) {
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");
        params.forEach((key, val) -> {
            urlBuilder.append(key)
                    .append("=")
                    .append(val)
                    .append("&");
        });
        return urlBuilder.substring(0, urlBuilder.length() - 1);
    }

    @Override
    public Map<String, Object> signatureForm(RequestProperties myRequestProperties, Map<String, Object> body) {
        return body;
    }

    @Override
    public Object signatureJson(RequestProperties myRequestProperties, Object body) {
        return body;
    }
}
