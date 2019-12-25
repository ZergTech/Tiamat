package com.zerg.tiamat.service.rpc.notify.impl;

import com.zerg.tiamat.common.http.HttpHelper;
import com.zerg.tiamat.common.http.MyRequestProperties;
import com.zerg.tiamat.config.RequestPropertiesMap;
import com.zerg.tiamat.dto.ServerJResponse;
import com.zerg.tiamat.service.rpc.notify.NotifyRpcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xuyang
 * @date : 2019-12-24 13:49
 */

@Service
public class NotifyRpcServiceImpl implements NotifyRpcService {

    @Resource
    private RequestPropertiesMap requestPropertiesMap;
    private static final Integer SUCCESS = 0;


    @Override
    public Boolean sendMessage(String subject, String text) {
        MyRequestProperties server = requestPropertiesMap.getProps("server");

        Map<String, Object> params = new HashMap<>();
        params.put("text", subject);
        //"截止到" + DateTimeUtils.getCurrentDateStr() + "，货币：btcusdt，分时线：1min，已经触发您设定的报警价位，" + "最高价格：7600 usdt，最低价格：7200 usdt，开盘价格：7300 usdt，收盘价格：7400 usdt，震荡幅度：1%，请留意。"
        params.put("desp", text);

        HttpHelper httpHelper = HttpHelper.create();
        ServerJResponse response = httpHelper.deserializeFromForm(server.getUrl(), params, ServerJResponse.class);

        return response.getErrno().equals(SUCCESS);
    }
}
