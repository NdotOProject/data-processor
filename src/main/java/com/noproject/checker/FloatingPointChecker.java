/*
 * 
 */
package com.noproject.checker;

import com.noproject.action.StringEditor;
import com.noproject.processor.StringProcessor;

/**
 *
 * @author
 */
public class FloatingPointChecker {

    private static final byte FLOATING_POINT = (byte) '.';
    private static final byte ZERO_CHAR = (byte) '0';

    private byte[] value;

    public FloatingPointChecker(byte[] arr) {
        if (arr == null) {
            this.value = new byte[0];
        } else {
            this.value = arr;
        }
    }

    public static boolean isFloatingPointNumber(String value) {
        return isFloatingPointNumber(StringProcessor.toByteArray(value));
    }

    public static boolean isFloatingPointNumber(byte[] arr) {
        return new FloatingPointChecker(arr).isFloatingPointNumber();
    }

    public boolean isFloatingPointNumber() {
        int pointIndex = indexOfPointChar(value);

        if (pointIndex < 0) {
            return false;
        }
        value = completeFloatingPointForm(value);

        StringEditor se = new StringEditor(new String(value));

        String regex = "\\" + Character.toString(FLOATING_POINT);

        String[] strArr = se.split(regex);

        byte[] left = strArr[0].getBytes(),
                right = strArr[1].getBytes();

        if (IntegerNumberChecker.isUnsignedInteger(right)) {
            if (IntegerNumberChecker.isIntegerNumber(left)) {
                return true;
            }
        }

        return false;
    }

    private byte[] completeFloatingPointForm(byte[] arr) {
        int pointIndex = indexOfPointChar(arr);

        if (pointIndex >= 0) {
            int arrLength = arr.length;

            StringEditor se = new StringEditor(new String(arr));

            if (pointIndex == 0) {
                se.insert(0, Character.toString(ZERO_CHAR));
            }

            if (arrLength == 1 || pointIndex == arrLength - 1) {
                se.append(Character.toString(ZERO_CHAR));
            }

            arr = se.toByteArray();
        }
        return arr;
    }

    private int indexOfPointChar(byte[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == FLOATING_POINT) {
                if (index != -1) {
                    return -1;
                }
                index = i;
            }
        }
        return index;
    }

}
