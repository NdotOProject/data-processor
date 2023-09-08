/*
 *
 */
package com.noproject.checker;

/**
 *
 * @author
 */
public class PrefixHandle {

    private static final byte POSITIVE = (byte) '+';// '+'

    private static final byte NEGATIVE = (byte) '-';// '-'

    public static int getPrefixType(char prefix) {
        if (prefix == NEGATIVE) {
            return -1;
        }
        if (prefix == POSITIVE
                || IntegerNumberChecker.isIntegerNumberChar(prefix)) {
            return 1;
        }
        return 0;
    }

    public static boolean isNegativePrefix(char prefix) {
        return getPrefixType(prefix) < 0;
    }

    public static boolean isPositivePrefix(char prefix) {
        return getPrefixType(prefix) > 0;
    }

}
