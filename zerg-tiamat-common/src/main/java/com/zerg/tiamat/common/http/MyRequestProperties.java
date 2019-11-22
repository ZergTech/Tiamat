package com.zerg.tiamat.common.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: luyushuang
 * Date: 2018/3/31
 * Time: 下午3:50
 * Description: No Description
 */
@Getter
@Setter
@ToString
public class MyRequestProperties implements RequestProperties {
    String url;
    Map<String, Object> args;
    String appSecret;
}
