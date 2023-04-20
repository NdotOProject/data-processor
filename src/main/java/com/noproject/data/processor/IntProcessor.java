/*
 * 
 */
package com.noproject.data.processor;

import java.util.regex.Pattern;

/**
 *
 * @author DELL
 */
public class IntProcessor {

    // cast int từ hệ 2, 8(byte), 10, 16(short), 32(int), 64(long)
    //Pattern.compile("^(?:-(?:[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))|(?:0|(?:[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))))(?:.\\d+|)$");
    private static final Pattern INT_PATTERN = Pattern.compile("^[-+]?[0-9]+");

    public static int getValueOrDefault(int value, int defaultValue) {
        return value != defaultValue ? value : defaultValue;
    }

    public static int safelyCast(long value, int defaultValue) {
        if (value >= Integer.MIN_VALUE
                && value <= Integer.MAX_VALUE) {
            return Integer.parseInt(String.valueOf(value));
        }
        return defaultValue;
    }

    public static int safelyCast(String value, int defaultValue) {
        if (value != null && INT_PATTERN.matcher(value).matches() == true) {
            return safelyCast(Long.parseLong(value), defaultValue);
        }
        return defaultValue;
    }

}
