package com.zerg.tiamat.common.http.filter;

/**
 * @author : xuyang
 * @date : 2019-09-19 17:03
 */
public class DefaultResponseFilter implements ResponseFilter {
    @Override
    public String filter(String s) {
        return s;
    }
}
