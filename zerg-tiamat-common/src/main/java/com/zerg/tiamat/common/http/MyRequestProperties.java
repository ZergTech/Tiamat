package com.zerg.tiamat.common.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;


@Getter
@Setter
@ToString
public class MyRequestProperties implements RequestProperties {
    String url;
    Map<String, Object> args;
    String appSecret;
}
