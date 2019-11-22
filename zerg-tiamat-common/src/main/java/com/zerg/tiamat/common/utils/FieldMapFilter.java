package com.zerg.tiamat.common.utils;

/**
 * @author : xuyang
 * @date : 2019-11-07 15:35
 */

@FunctionalInterface
public interface FieldMapFilter {
    Object filter(String key, Object value);
}
