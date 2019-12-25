package com.zerg.tiamat.common.utils;

import com.google.common.collect.Maps;
import com.zerg.tiamat.common.http.HttpHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @author : xuyang
 * @date : 2019-12-19 14:49
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class MailUtilsTest {

    private static final String POST_URL = "https://sc.ftqq.com/SCU70956T0c15fbd0c9116e74a9e7e93fac9539ec5e0037cfebae0.send";

    @Test
    public void sendEmail() {
        MailUtils.sendEmail("ex1stance@outlook.com", "【比特币价格震荡报警】", "【比特币价格震荡报警】2019-12-18 12:10:11 比特币在5分钟之内震荡超过1%，开盘：7600usdt，收盘：7800usdt，最高：8100usdt，最低：7200usdt");
    }

    @Test
    public static void main(String[] args) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("text", "价格震荡报警");
        params.put("desp", "截止到" + DateTimeUtils.getCurrentDateStr() + "，货币：btcusdt，分时线：1min，已经触发您设定的报警价位，" + "最高价格：7600 usdt，最低价格：7200 usdt，开盘价格：7300 usdt，收盘价格：7400 usdt，震荡幅度：1%，请留意。");

        HttpHelper httpHelper = HttpHelper.create();
        httpHelper.doPostByForm(POST_URL, params);
    }
}
