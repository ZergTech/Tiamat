package com.zerg.tiamat.common.http;


import com.zerg.tiamat.common.http.filter.DefaultResponseFilter;
import com.zerg.tiamat.common.http.sign.DefaultSign;

/**
 * @author : xuyang
 * @date : 2019-09-20 14:34
 */
public interface HttpRequest {

    DefaultResponseFilter DEFAULT_RESPONSE_FILTER = new DefaultResponseFilter();
    DefaultSign DEFAULT_SIGN = new DefaultSign();

}
