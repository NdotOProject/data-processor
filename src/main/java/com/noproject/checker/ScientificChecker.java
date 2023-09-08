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
public class ScientificChecker {

    private static final byte SCIENTIFIC = (byte) 'e';

    private byte[] value = new byte[0];

    public ScientificChecker(byte[] arr) {
        this.value = arr;
    }

    public boolean isScientificNumber() {
        StringEditor se = new StringEditor(new String(value).toLowerCase());

        String scientificStr = Character.toString(SCIENTIFIC);

        if (se.contains(scientificStr)) {
            String[] strArr = se.split(scientificStr);

            byte[] left = strArr[0].getBytes(),
                    right = strArr[1].getBytes();

            if (IntegerNumberChecker.isIntegerNumber(right)) {
                if (FloatingPointChecker.isFloatingPointNumber(left)) {
                    return true;
                } else if (IntegerNumberChecker.isIntegerNumber(left)) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    public static boolean isScientificNumber(byte[] arr) {
        return new ScientificChecker(arr).isScientificNumber();
    }

    public static boolean isScientificNumber(String value) {
        return isScientificNumber(StringProcessor.toByteArray(value));
    }

}
