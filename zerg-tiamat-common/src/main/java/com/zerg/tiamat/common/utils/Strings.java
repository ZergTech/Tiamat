package com.zerg.tiamat.common.utils;

import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : xuyang
 * @date : 2019-09-26 17:04
 */

public class Strings {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public static final String EMPTY = "";

    public Strings() {
    }

    public static String valueOf(Object object) {
        return object == null ? "" : object.toString();
    }

    public static String logInfo(String log) {
        Integer logLength = 60;
        logLength -= (log.length() * 2);
        logLength /= 2;
        logLength = logLength > 0 ? logLength : 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < logLength; i++) {
            stringBuilder.append("=");
        }
        String prefix = stringBuilder.toString();
        stringBuilder.append(log);
        stringBuilder.append(prefix);
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    public static String logERROR(String log) {
        return logERROR(log, null);
    }

    public static String logERROR(String log, Exception exp) {
        Integer logLength = 60;
        logLength -= (log.length() * 2);
        logLength /= 2;
        logLength = logLength > 0 ? logLength : 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < logLength; i++) {
            stringBuilder.append("!");
        }
        String prefix = stringBuilder.toString();
        stringBuilder.append(log);
        stringBuilder.append(prefix);
        if (Objects.nonNull(exp)) {
            stringBuilder.append("\n错误原因:\n");
            stringBuilder.append(exp);
        }
        return stringBuilder.toString();
    }


    public static String lineToHump(String str) {
        String tmp = str.toLowerCase();
        Matcher matcher = linePattern.matcher(tmp);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String dquote(String str) {
        return '"' + str + '"';
    }

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String quote(String str) {
        return '\'' + str + '\'';
    }

    public String toRootUpperCase(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public static String trimToNull(String str) {
        String ts = str == null ? null : str.trim();
        return isEmpty(ts) ? null : ts;
    }

    public static String join(Iterable<?> iterable, char separator) {
        return iterable == null ? null : join(iterable.iterator(), separator);
    }

    public static String join(Iterator<?> iterator, char separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return Objects.toString(first, "");
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(first);
                }

                while (iterator.hasNext()) {
                    buf.append(separator);
                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }

                return buf.toString();
            }
        }
    }
}
