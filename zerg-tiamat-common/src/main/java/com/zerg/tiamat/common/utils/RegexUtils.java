package com.zerg.tiamat.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexUtils {
    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";;

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证包含中文
     */
    public static final String REGEX_CONTAIN_CHINESE = "[\u4e00-\u9fa5]";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：验证包含等号
     */
    public static final String REGEX_ISCONTAIN_EQUALS = "^*==$";

    /**
     * 正则表达式：验证IP地址
     */
    public static final Pattern pattern = Pattern.compile("[0-9]*");


    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumer(String str){
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 判断是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern compile = Pattern.compile(REGEX_CONTAIN_CHINESE);
        Matcher matcher = compile.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断末尾是否为==
     *
     * @param str
     * @return
     */
    public static boolean isContainEquals(String str) {
        Pattern pattern = Pattern.compile(REGEX_ISCONTAIN_EQUALS);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}

