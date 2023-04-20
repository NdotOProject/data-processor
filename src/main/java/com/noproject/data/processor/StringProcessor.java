/*
 *
 */
package com.noproject.data.processor;

/**
 *
 *
 */
public class StringProcessor {

    public static final String EMTPY_VALUE = "";

    public static boolean isNullOrEmpty(String value) {
        if (value != null) {
            return value.isEmpty();
        }
        return true;
    }

    public static boolean isNullOrWhiteSpaceOnly(String value) {
        if (value != null) {
            return value.isBlank();
        }
        return true;
    }

    public static boolean hasWhiteSpace(String value) {
        if (isNullOrWhiteSpaceOnly(value) == true) {
            return false;
        }
        final byte whiteSpace = 0x0020;
        return value.contains(Character.toString(whiteSpace));
    }

    public static boolean isNullOrHasWhiteSpace(String value) {
        if (value != null) {
            return hasWhiteSpace(value);
        }
        return true;
    }

    public static String getNonNullValue(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }

}
