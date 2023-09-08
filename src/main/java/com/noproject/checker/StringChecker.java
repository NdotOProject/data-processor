/*
 */
package com.noproject.checker;

/**
 *
 * @author
 */
public class StringChecker {

    private StringChecker() {
    }

    public static boolean isNullOrEmpty(String value) {
        if (value != null) {
            return value.isEmpty();
        }
        return true;
    }

    public static boolean isNullOrNoContent(String value) {
        if (value != null) {
            return value.isBlank();
        }
        return true;
    }

    public static boolean isNullOrWhiteSpaceOnly(String value) {
        if (value != null) {
            return value.isBlank();
        }
        return true;
    }

    public static boolean isNullOrHasWhiteSpace(String value) {
        if (value != null) {
            return hasWhiteSpace(value);
        }
        return true;
    }

    public static boolean hasWhiteSpace(String value) {
        if (isNullOrNoContent(value) == true) {
            return false;
        }
        final byte whiteSpace = 0x0020;
        return value.contains(Character.toString(whiteSpace));
    }
}
