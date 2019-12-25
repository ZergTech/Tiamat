package com.zerg.tiamat.entity;

import com.google.common.base.Preconditions;
import com.zerg.tiamat.common.utils.DateTimeUtils;
import com.zerg.tiamat.common.utils.Strings;
import com.zerg.tiamat.dao.Alarm;
import com.zerg.tiamat.service.local.TemplateMessage;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author : xuyang
 * @date : 2019-12-24 20:34
 */

@Data
@Builder
public class CandlestickMessageModel implements TemplateMessage {

    private Alarm alarm;
    private String currency;
    private String interval;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal amount;
    private BigDecimal volume;

    private static final String SUBJECT = "行情震荡报警";
    private static final String TEMPLATE = "亲爱的用户:\n" +
            "\n" +
            "&emsp;&emsp;${user}\n" +
            "\n" +
            "&emsp;&emsp;您好，截止到北京时间：${time}，货币：【${currency}】在【${interval}分时线】上的行情触发了您设定的报警（${alarm}）。\n" +
            "\n" +
            "- 【开盘价】${open}美元\n" +
            "\n" +
            "- ###【收盘价】${close}美元\n" +
            "\n" +
            "- 【最高价】${high}美元\n" +
            "\n" +
            "- 【最低价】${low}美元\n" +
            "\n" +
            "- 【震荡幅度】${shocks}%\n" +
            "\n" +
            "- 【成交量】${amount}\n" +
            "\n" +
            "- 【成交额】${volume}\n" +
            "\n" +
            "&emsp;&emsp;股市有风险，入市需谨慎。";

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private static final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000);
    private static final BigDecimal ONE_MILLION = BigDecimal.valueOf(1000_000);

    @Override
    public String getSubject() {
        return SUBJECT;
    }

    @Override
    public String getMessage() {
        checkArguments();
        String alarmStr = alarm.getLowPrice()+","+alarm.getHighPrice()+","+alarm.getShockRange().multiply(ONE_HUNDRED)+"%";
        BigDecimal shocks = close.subtract(open).multiply(ONE_HUNDRED).divide(open, 3, BigDecimal.ROUND_CEILING);
        String amountStr = scientificFormat(amount);
        String volumeStr = scientificFormat(volume);
        return TEMPLATE.replaceFirst("\\$\\{user}", alarm.getName())
                .replaceFirst("\\$\\{time}", DateTimeUtils.getCurrentDateStr())
                .replaceFirst("\\$\\{currency}", currency.toUpperCase())
                .replaceFirst("\\$\\{interval}", interval)
                .replaceFirst("\\$\\{alarm}", alarmStr)
                .replaceFirst("\\$\\{open}", open.toString())
                .replaceFirst("\\$\\{close}", close.toString())
                .replaceFirst("\\$\\{high}", high.toString())
                .replaceFirst("\\$\\{low}", low.toString())
                .replaceFirst("\\$\\{shocks}", shocks.toString())
                .replaceFirst("\\$\\{amount}", amountStr)
                .replaceFirst("\\$\\{volume}", volumeStr);
    }

    private String scientificFormat(BigDecimal number) {
        String result;
        if (number.compareTo(ONE_MILLION) > 0){
            result  = number.divide(ONE_MILLION, 2, BigDecimal.ROUND_CEILING).toString() + "M";
        }else if (number.compareTo(ONE_THOUSAND) > 0){
            result  = number.divide(ONE_THOUSAND, 2, BigDecimal.ROUND_CEILING).toString() + "K";
        }else {
            result = number.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_CEILING).toString();
        }
        return result;
    }

    private void checkArguments() {
        Preconditions.checkArgument(Objects.nonNull(alarm));
        Preconditions.checkArgument(Strings.isNotBlank(currency));
        Preconditions.checkArgument(Strings.isNotBlank(interval));
        Preconditions.checkArgument(Objects.nonNull(high));
        Preconditions.checkArgument(Objects.nonNull(low));
        Preconditions.checkArgument(Objects.nonNull(open));
        Preconditions.checkArgument(Objects.nonNull(close));
        Preconditions.checkArgument(Objects.nonNull(amount));
        Preconditions.checkArgument(Objects.nonNull(volume));
    }
}
