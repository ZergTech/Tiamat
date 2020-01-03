package com.zerg.tiamat.model;

import com.zerg.tiamat.common.utils.RequestUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : xuyang
 * @date : 2019-12-27 10:37
 */

@Slf4j
public class Senator implements ICandlestickEventListener {
    @Getter
    private String name;

    public Senator(){
        this.name = RequestUtils.generateRequestId();
    }

    public Senator(String name){
        this.name = name;
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param candlestickEvent the input argument
     */
    @Override
    public void accept(ICandlestickEvent candlestickEvent) {

    }
}
