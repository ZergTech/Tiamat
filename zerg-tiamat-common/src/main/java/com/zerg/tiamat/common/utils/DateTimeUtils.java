package com.zerg.tiamat.common.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeUtils {

    public static final long TEN_MINUTES = 10 * 60 * 1000L;
    public static final double ONE_HOUR = 60 * 60 * 1000L;
    public static final long ONE_DAY = 24 * 60 * 60 * 1000L;

    public static final DecimalFormat df = new DecimalFormat("#0.00");
    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_FORMAT_NO_SPLIT = "yyyyMMdd";
    /**
     * 日期格式：yyyyMMddHHmmss
     */
    public static final String DATE_TIME_FORMAT_NO_SPLIT = "yyyyMMddHHmmss";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String DATE_TIME_MS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Adds or subtracts the specified amount of time to the given calendar field, based on the calendar's rules. For
     * example, to subtract 5 days from the current time of the calendar, you can achieve it by calling:
     * <p>
     * <code>add(Calendar.DAY_OF_MONTH, -5)</code>.
     *
     * @param date   the date of before the changed.
     * @param field  the calendar field.
     * @param amount the amount of date or time to be added to the field.
     * @return the date
     */
    public static Date add(final Date date, Integer field, Integer amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(field, amount);

        return calendar.getTime();
    }

    /**
     * add days
     *
     * @param date the date of before the changed.
     * @param days the amount of date to be added to the field Calendar.DATE .
     * @return the date
     */
    public static Date addDate(final Date date, Integer days) {
        return add(date, Calendar.DATE, days);
    }

    /**
     * 按指定的格式，将日期转换成为字符
     *
     * @param date   日期
     * @param format 日期格式
     * @return 字符串的日期
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }

        if (isEmpty(format)) {
            format = DATE_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 判断是否空
     *
     * @param str 字符串
     * @return 是 true，否false
     */
    private static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 将日期转换成为字符（yyyy-MM-dd）
     *
     * @param date date
     * @return string
     */
    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }

    /**
     * 今天日期的字符（yyyy-MM-dd）
     *
     * @return 今天日期的字符（yyyy-MM-dd）
     */
    public static String today() {
        return formatDate(new Date());
    }

    /**
     * 获得 mm-dd格式的当前日期
     *
     * @return
     */
    public static String getMonthAndDay() {
        String today = today();
        String[] split = today.split("-");

        return split[1].concat("_").concat(split[2]);
    }

    /**
     * 将日期转换成为字符（yyyy-MM-dd HH:mm:ss）
     *
     * @param date date
     * @return string
     */
    public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_FORMAT);
    }

    /**
     * 当前时间的字符（yyyy-MM-dd HH:mm:ss）
     *
     * @return 当前时间的字符（yyyy-MM-dd HH:mm:ss）
     */
    public static String now() {
        return formatDateTime(new Date());
    }

    /**
     * 将日期转换成为字符（yyyy-MM-dd HH:mm:ss.SSS）
     *
     * @param date date
     * @return 日期时间的字符
     */
    public static String formatDateTimeMs(Date date) {
        return format(date, DATE_TIME_MS_FORMAT);
    }

    public static Date getDateValue(Object object) {
        return null == object ? null : (Date) object;
    }

    /**
     * 按指定的格式，将字符转换为日期
     *
     * @param dateString 字符转
     * @param format     格式
     * @return 日期
     */
    public static Date parseDate(String dateString, String format) {
        if (isEmpty(format)) {
            return null;
        }

        try {
            return new SimpleDateFormat(format).parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符（yyyy-MM-dd）转换为日期
     *
     * @param dateString 字符转
     * @return 日期
     */
    public static Date parseDate(String dateString) {
        return parseDate(dateString, DATE_FORMAT);
    }

    /**
     * @Description: 将 yyyy-MM-dd 字符串日期转换为 时间戳 秒
     * @Param:
     * @return:
     * @Date: 2019-06-12
     */
    public static Long parseDateToLong(String dateString) {
        return parseDate(dateString, DATE_FORMAT).getTime() / 1000;
    }

    /**
     * @Description: 将 yyyy-MM-dd 字符串日期转换为 时间戳 秒
     * @Param:
     * @return:
     * @Date: 2019-06-12
     */
    public static Long parseDateTimeToLong(String dateString) {
        return parseDate(dateString, DATE_TIME_FORMAT).getTime() / 1000;
    }

    /**
     * @Description: 将 yyyy-MM-dd hh:MM:ss 字符串日期转换为 时间戳 秒
     * @Param:
     * @return:
     * @Date: 2019-06-12
     */
    public static Long parseStringDateToSeconds(String dateString) {
        return parseDate(dateString, DATE_TIME_FORMAT).getTime() / 1000;
    }

    /**
     * @Description: 将 yyyy-MM-dd hh:MM:ss 字符串日期转换为 时间戳 豪秒
     * @Param:
     * @return:
     * @Date: 2019-06-12
     */
    public static Long parseStringDateToMills(String dateString) {
        return parseDate(dateString, DATE_TIME_FORMAT).getTime();
    }

    /**
     * 将字符（yyyy-MM-dd HH:mm:ss）转换为日期
     *
     * @param dateString 字符转
     * @return 日期
     */
    public static Date parseDateTime(String dateString) {
        return parseDate(dateString, DATE_TIME_FORMAT);
    }

    /**
     * @Description: 将字符串日期转换为 时间戳 豪秒
     * @Param:
     * @return:
     * @Date: 2019-06-12
     */
    public static Long parseDateToMilsLong(String dateString) {
        return parseDate(dateString, DATE_TIME_FORMAT).getTime();
    }


    /**
     * milliseconds 转化为日期
     *
     * @param date 日期
     * @return 日期
     */
    public static Date toDate(Long date) {
        if (date == null) {
            return null;
        }
        return new Date(date);
    }

    /**
     * 获取当前时间毫秒数 milliseconds
     *
     * @return 当前时间毫秒数
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @param dateFormatPattern 日期转换格式
     * @return 时间字符串
     */
    public static String getCurrentDateStr(String dateFormatPattern) {
        if (isEmpty(dateFormatPattern)) {
            dateFormatPattern = DATE_TIME_FORMAT;
        }
        return format(new Date(), dateFormatPattern);
    }

    public static String getCurrentDateStr() {

        return format(new Date(), DATE_TIME_FORMAT);
    }

    /**
     * 获取服务器时间
     *
     * @param operateTimeStr 客户端的时间
     * @return 服务器时间
     */
    public static Date getServerTime(String operateTimeStr) {
        Date serverTime = new Date();
        if (isEmpty(operateTimeStr)) {
            return serverTime;
        }

        Date operateTime = parseDate(operateTimeStr, DATE_TIME_FORMAT);

        if (operateTime == null) {
            operateTime = parseDate(operateTimeStr, DATE_TIME_MS_FORMAT);
        }

        Long interval = 0L;
        if (operateTime != null) {
            interval = operateTime.getTime() - serverTime.getTime();
        }
        if (operateTime != null && operateTime.after(serverTime) && TEN_MINUTES < interval) {
            return serverTime;
        } else if (operateTime != null && operateTime.before(serverTime) && ONE_DAY < Math.abs(interval)) {
            return serverTime;
        } else {
            return operateTime;
        }
    }

    /**
     * 获取一天的开始时间
     *
     * @param date
     * @return
     */
    public static Date getDateOfStartTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();

    }

    /**
     * 获取一天的结束时间
     *
     * @param date
     * @return
     */
    public static Date getDateOfEndTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    /**
     * @Description: 本周的开始时间
     * @Param:
     * @return:
     * @Date: 2019-06-26
     */
    public static Date getBeginDayOfWeek(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDateOfStartTime(cal.getTime());
    }

    /**
     * @Description: 本周的结束时间
     * @Param:
     * @return:
     * @Date: 2019-06-26
     */
    public static Date getEndDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDateOfStartTime(weekEndSta);
    }

    /**
     * 获取3天前时间
     *
     * @return
     */
    public static Date getBeginOf3DaysAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -3);
        return getDateOfStartTime(calendar.getTime());
    }

    /**
     * @Description: 获取当前日期的上一周的开始时间
     * @Param: date 当前日期
     * @return:
     * @Date: 2019-06-26
     */
    public static Date getBeginDayOfLastWeek(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofWeek == 1) {
            dayofWeek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofWeek - 7);
        return getDateOfStartTime(cal.getTime());
    }

    /**
     * @Description: 获取当前日期的上一周的结束时间
     * @Param: date 当前日期
     * @return:
     * @Date: 2019-06-26
     */
    public static Date getEndDayOfLastWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDateOfStartTime(weekEndSta);
    }


    /**
     * 获取本月的开始时间
     *
     * @param date
     * @return
     */
    public static String getMonthOfStartTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return dateFormat.format(calendar.getTime());

    }

    /**
     * 获取本月的结束时间
     *
     * @param date
     * @return
     */
    public static String getMonthOfEndTime(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获得这个月的天数
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * @Description: 获得当前时间是周几
     * @Param:
     * @return:
     * @Date: 2019-06-11
     */
    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得星期几
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            return 7;
        } else {
            return week;
        }
    }

    /**
     * @Description: 查询当前时间的小时
     * @Param:
     * @return:
     * @Date: 2019-08-05
     */
    public static int getHoursByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @Description: 获取一段时间的日期集合 yyyy-MM-dd
     * @Param:
     * @return:
     * @Date: 2019-06-17
     */
    public static List<String> findDates(Date startTime, Date endTime) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        List<String> dateList = new ArrayList<String>();
        //开始时间
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(startTime);

        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endTime);
        dateList.add(format.format(calBegin.getTime()));
        // 每次循环给calBegin日期加一天，直到calBegin.getTime()时间等于dEnd
        while (endTime.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(format.format(calBegin.getTime()));
        }

        return dateList;
    }

    /**
     * 获取时间差 小时
     *
     * @param dateStart 开始时间
     * @param dateEnd   结束时间
     * @return 差值
     */
    public static String getDateDValue(Date dateStart, Date dateEnd) {
        long dv = dateEnd.getTime() - dateStart.getTime();
        double v = dv / ONE_HOUR;

        return df.format(v);
    }
}
