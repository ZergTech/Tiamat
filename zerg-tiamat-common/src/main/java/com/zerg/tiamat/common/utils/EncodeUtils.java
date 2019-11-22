package com.zerg.tiamat.common.utils;

import com.zerg.tiamat.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.Validate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
public class EncodeUtils {
    /**
     * The Constant ALPHABET.
     */
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * The Constant DEFAULT_ENCODING UTF-8.
     */
    public static final String UTF8_ENCODING = "UTF-8";

    /**
     * Instantiates a new encodes.
     */
    private EncodeUtils() {
    }

    /**
     * Hex编码, byte[]转String.
     *
     * @param input the input
     * @return the string
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码, String转byte[].
     *
     * @param input the input
     * @return the byte[]
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            log.error("decodeHex(String)", e);
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * Base64编码, byte[]转String.
     *
     * @param input the input
     * @return the string
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     *
     * @param input the input
     * @return the string
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码, String转byte[].
     *
     * @param input the input
     * @return the byte[]
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * Base62(0_9A_Za_z)编码数字, long转String.
     *
     * @param num the num
     * @return the string
     */
    public static String encodeBase62(long num) {
        return alphabetEncode(num, 62);
    }

    /**
     * Base62(0_9A_Za_z)解码数字, String转long.
     *
     * @param str the str
     * @return the long
     */
    public static long decodeBase62(String str) {
        return alphabetDecode(str, 62);
    }

    /**
     * Alphabet encode.
     *
     * @param num  the num
     * @param base the base
     * @return the string
     */
    private static String alphabetEncode(long num, int base) {
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        for (; num > 0; num /= base) {
            sb.append(ALPHABET.charAt((int) (num % base)));
        }

        return sb.toString();
    }

    /**
     * Alphabet decode.
     *
     * @param str  the str
     * @param base the base
     * @return the long
     */
    private static long alphabetDecode(String str, int base) {
        Validate.notBlank(str);

        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result += ALPHABET.indexOf(str.charAt(i)) * Math.pow(base, i);
        }

        return result;
    }

    /**
     * Html 转码.
     *
     * @param html the html
     * @return the string
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     *
     * @param htmlEscaped the html escaped
     * @return the string
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     *
     * @param xml the xml
     * @return the string
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    /**
     * Xml 解码.
     *
     * @param xmlEscaped the xml escaped
     * @return the string
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * Csv 转码.
     *
     * @param csv the csv
     * @return the string
     */
    public static String escapeCsv(String csv) {
        return StringEscapeUtils.escapeCsv(csv);
    }

    /**
     * Csv 解码.
     *
     * @param csvEscaped the csv escaped
     * @return the string
     */
    public static String unescapeCsv(String csvEscaped) {
        return StringEscapeUtils.unescapeCsv(csvEscaped);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     *
     * @param part the part
     * @return the string
     */
    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, UTF8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            log.error("urlEncode(String)", e);
            throw new BizException(e);
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     *
     * @param part the part
     * @return the string
     */
    public static String urlDecode(String part) {
        try {
            return URLDecoder.decode(part, UTF8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            log.error("urlDecode(String)", e);
            throw new BizException(e);
        }
    }
}
