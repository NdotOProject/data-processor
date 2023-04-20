/*
 *
 */
package com.noproject.data.processor;

import java.util.regex.Pattern;

/**
 *
 * @author DELL
 */
public class LongProcessor {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[-+]?[0-9]+");

    public static boolean isLessThanOrEqualMax(String value) {
        if (StringProcessor.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        if (value.startsWith("-") == true) {
            return isGreatThanOrEqualMin(value);
        }
        char[] chrValue = value.toCharArray();
        char[] chrMaxLong = String.valueOf(Long.MAX_VALUE).toCharArray();
        if (chrValue.length < chrMaxLong.length) {
            return true;
        }
        for (int i = 0; i < chrMaxLong.length; i++) {
            if (chrValue[i] > chrMaxLong[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGreatThanOrEqualMin(String value) {
        if (StringProcessor.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        if (value.startsWith("-") == false) {
            return isLessThanOrEqualMax(value);
        }
        char[] chrValue = value.toCharArray();
        char[] chrMinLong = String.valueOf(Long.MIN_VALUE).toCharArray();
        if (chrValue.length < chrMinLong.length) {
            return true;
        }
        for (int i = 0; i < chrMinLong.length; i++) {
            if (chrValue[i] < chrMinLong[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkLong(String value) {

        return false;
    }

    public static long safelyCast(String value, long defaultValue) {

        return defaultValue;
    }
}
