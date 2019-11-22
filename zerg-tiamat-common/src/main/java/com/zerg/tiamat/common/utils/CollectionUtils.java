package com.zerg.tiamat.common.utils;

/**
 * @author : xuyang
 * @date : 2019-09-26 17:27
 */
public class CollectionUtils {
    /**
     * 阿里推荐的系数
     */
    public static final Double initialCapacity=0.75;

    /**
     * 初始化集合长度
     * @param size 集合大小
     * @return initialCapacity
     */
    public static Integer getInitSize(Integer size){
        return  ((Double)Math.ceil(size/initialCapacity+1)).intValue();
    }
}
