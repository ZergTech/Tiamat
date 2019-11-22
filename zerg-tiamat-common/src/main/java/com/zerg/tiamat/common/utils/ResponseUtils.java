package com.zerg.tiamat.common.utils;


import com.zerg.tiamat.common.http.response.BaseResponse;

import java.util.Objects;
import java.util.Optional;

/**
 * @author : xuyang
 * @date : 2019-11-08 14:36
 */
public class ResponseUtils {

    private static final Integer SUCCESS = 0;

    public static <T> Optional<T> getData(BaseResponse<T> response, Integer successVal) {
        if (Objects.isNull(successVal)) {
            return Optional.empty();
        }

        if (successVal.compareTo(response.getCode()) != 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(response.getData());
    }

    public static <T> Optional<T> getData(BaseResponse<T> response) {
        return getData(response, SUCCESS);
    }
}
