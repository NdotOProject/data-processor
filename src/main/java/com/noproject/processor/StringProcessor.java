/*
 *
 */
package com.noproject.processor;

import com.noproject.action.StringEditor;
import com.noproject.checker.StringChecker;

/**
 *
 *
 */
public class StringProcessor {

    private StringProcessor() {
    }

    public static String ignoreStringAndGet(
            String value, String ignoreStr) {
        if (!value.contains(ignoreStr)) {
            return value;
        }
        StringEditor se = new StringEditor(value);

        se.delete(ignoreStr);

        return ignoreStringAndGet(se.toString(), ignoreStr);
    }

    public static String getNonNullValue(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static char[] toCharArray(String value) {
        if (StringChecker.isNullOrEmpty(value)) {
            return new char[]{};
        }
        return value.toCharArray();
    }

    public static byte[] toByteArray(String value) {
        if (StringChecker.isNullOrEmpty(value)) {
            return new byte[]{};
        }
        return value.getBytes();
    }

}
