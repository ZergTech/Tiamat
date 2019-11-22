package com.zerg.tiamat.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author : xuyang
 * @date : 2019-09-26 18:47
 */
public class LocalDateTimeUtils {
    /**
     * 毫秒格式
     */
    private final static DateTimeFormatter STANDARD_FORMAT_MS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.CHINA);

    /**
     * 秒格式
     */
    private final static DateTimeFormatter STANDARD_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    /**
     * 从Date转换到LocalTimeDate
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 从LocalDateTime转换到Date
     *
     * @param localDateTime
     * @return
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 毫秒格式的字符串转LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime parseByMs(String dateStr) {
        return LocalDateTime.parse(dateStr, STANDARD_FORMAT_MS);
    }

    /**
     * 秒格式的字符串转LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime parse(String dateStr) {
        return LocalDateTime.parse(dateStr, STANDARD_FORMAT);
    }

    /**
     * 输出毫秒格式的字符串
     *
     * @param localDateTime
     * @return
     */
    public static String formatByMs(LocalDateTime localDateTime) {
        return localDateTime.format(STANDARD_FORMAT_MS);
    }

    /**
     * 输出秒格式的字符串
     *
     * @param localDateTime
     * @return
     */
    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(STANDARD_FORMAT);
    }


    /**
     * 字符串时间转为秒  这里都是北京时间
     *
     * @param localDateTime yyyy-MM-dd HH:mm:ss
     * @return .now().toEpochSecond(ZoneOffset.of ( " + 8 "))
     */
    public static Long transfer(String localDateTime) {
        return parse(localDateTime).toEpochSecond(ZoneOffset.of("+8"));
    }


    /**
     * 字符串转为毫秒  这里都是北京时间
     *
     * @param localDateTime yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Long transferByMs(String localDateTime) {
        return parse(localDateTime).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


}
