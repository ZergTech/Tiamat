package com.zerg.tiamat.common.http;

import java.util.Map;

public interface RequestProperties {
    String getUrl();

    void setUrl(String url);

    Map<String, Object> getArgs();

    void setArgs(Map<String, Object> args);

    String getAppSecret();

    void setAppSecret(String appSecret);

}
