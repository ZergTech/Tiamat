package com.zerg.tiamat.config;

import com.zerg.tiamat.common.http.MyRequestProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("request")
@Component
@Slf4j
public class RequestPropertiesMap extends HashMap<String, Object> {

    public MyRequestProperties getProps(String name) {
        Map<String, Object> root = (Map<String, Object>) get(name);
        MyRequestProperties props = new MyRequestProperties();
        props.setUrl((String) root.get("url"));

        Map<String, Object> args;
        Object argsObject = root.get("args");
        if(argsObject instanceof Map){
            args = new HashMap<> ((Map<String, Object>) root.get("args"));
        }else{
            args = new HashMap<>();
        }
        props.setArgs(args);

        if (root.containsKey("appsecret")) {
            props.setAppSecret((String) root.get("appsecret"));
        }
        return props;
    }
}
