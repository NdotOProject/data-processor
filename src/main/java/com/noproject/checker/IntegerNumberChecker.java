/*
 *
 */
package com.noproject.checker;

/**
 *
 * @author
 */
public class IntegerNumberChecker {

    private static final byte[] DIGITS
            = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9'};

    public static boolean isIntegerNumberChar(char number) {
        int index = number - DIGITS[0];
        if (index >= 0 && index <= 9) {
            return number == DIGITS[index];
        }
        return false;
    }

    public static boolean isUnsignedInteger(byte[] arr) {
        for (byte num : arr) {
            if (isIntegerNumberChar((char) num) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIntegerNumber(byte[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        char firstChar = (char) arr[0];
        if (arr.length == 1) {
            return isIntegerNumberChar(firstChar);
        }
        if (PrefixHandle.getPrefixType(firstChar) != 0) {
            byte[] copyArr = new byte[arr.length - 1];
            int index = 0;
            for (int i = 1; i < arr.length; i++) {
                copyArr[index++] = arr[i];
            }
            arr = copyArr;
        }
        return isUnsignedInteger(arr);
    }

}
