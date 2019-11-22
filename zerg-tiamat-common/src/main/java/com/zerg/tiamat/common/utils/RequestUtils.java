package com.zerg.tiamat.common.utils;

import java.util.UUID;

/**
 * FileName : UtilRequest
 * <p>
 * ProjectName : xinche-after-mdcs
 * <p>
 * PackageName : com.maodou.after.mdcs.request
 * <p>
 * Description : requestId生成
 *
 * @author : daisenrong
 * @version : 1.0.0
 * @date : 2018/11/16 21:48
 */
public class RequestUtils {
    /** requestId的最大长度 */
    public static final int MAX_LENGTH = 32;

    public static final String REQUEST_ID_KEY = "requestId";

    /**
     * 生成requestId
     */
    public static String generateRequestId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
