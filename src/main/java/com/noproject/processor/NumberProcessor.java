/* Number :
 * Byte,
 * Short,
 * Integer, AtomicInteger, BigInteger,
 * Long, AtomicLong, LongAccumulator, LongAdder
 * Float,
 * Double, DoubleAccumulator, DoubleAdder,
 * BigDecimal
 * 
 *
 * Byte (8 bit) (-128 -> 127)
 * Short (16 bit) (-32768 -> 32767)
 * Int / Integer (32 bit) (-2^31 -> 2^31-1)
 * Long (64 bit) 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 */
package com.noproject.processor;

import com.noproject.checker.StringChecker;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Class này chứa các method kiểm tra một chuỗi có hợp lệ với kiểu dữ liệu số
 * không.
 * <p>
 * Các chuỗi chỉ được phép chứa các kí tự số. Các kí tự khác kí tự số được chấp
 * nhận :
 * <p>
 * underscore '_' (không ảnh hưởng đến giá trị của số, sẽ bị xóa đi).
 * <p>
 * plus '+' (cho biết đây là số dương).
 * <p>
 * minus '-' (cho biết đây là số âm).
 * <p>
 * dot '.' (cho biết đây là số có dạng Floating Point Number).
 * <p>
 * 'e' (cho biết đây là số có dạng Scientific Number).
 * <p>
 * 'f' (cho biết đây là số có kiểu dữ liệu float).
 * <p>
 * 'd' (cho biết đây là số có kiểu dữ liệu double).
 *
 *
 *
 */
public class NumberProcessor {

    private static final byte UNDERSCORE = 0x005F;// '_'

    private static final byte POSITIVE = 0x002B;// '+'

    private static final byte NEGATIVE = 0x002D;// '-'

    private static final byte FLOATING_POINT = 0x002E;// '.'

    private static final byte SCIENTIFIC = 0x0065;// 'e'

    private static final byte FLOAT_SIGN = 0x0066;// 'f'

    private static final byte DOUBLE_SIGN = 0x0064;// 'd'

    private static final byte[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static byte[] toByteArray(String value) {
        if (StringChecker.isNullOrHasWhiteSpace(value)) {
            return new byte[]{};
        }
        return StringProcessor.toByteArray(value);
    }

    private static boolean isValidUnderscores(String value) {
        byte[] byteArr = toByteArray(value);

        if (byteArr.length == 0) {
            return false;
        }

        if (byteArr[0] == UNDERSCORE) {
            return false;
        }

        if (byteArr[byteArr.length - 1] == UNDERSCORE) {
            return false;
        }

        return true;
    }

    protected static String removeUnderscores(String value) {
        if (isValidUnderscores(value) == false) {
            return "";
        }

        byte[] byteArr = toByteArray(value);
        int arrLength = byteArr.length;
        int countUnderscore = 0;
        for (int i = 0; i < arrLength; i++) {
            if (byteArr[i] == UNDERSCORE) {
                countUnderscore++;
            }
        }

        if (countUnderscore > 0) {
            byte[] newArr = new byte[arrLength - countUnderscore];
            int index = 0;
            for (int i = 0; i < arrLength; i++) {
                if (byteArr[i] != UNDERSCORE) {
                    newArr[index++] = byteArr[i];
                }
            }
            return new String(newArr);
        }
        return value;
    }

    private static boolean isNumberCharacter(char number) {
        int index = number - '0';
        if (index >= 0 && index <= 9) {
            return number == DIGITS[index];
        }
        return false;
    }

    public static boolean isBasicNumber(String value) {
        final byte[] byteArr = toByteArray(removeUnderscores(value));
        boolean result = false;
        for (int i = 0; i < byteArr.length; i++) {
            result = isNumberCharacter((char) byteArr[i]);
            if (result == false) {
                return result;
            }
        }
        return result;
    }

    public static boolean isNegativeNumber(String value) {
        return false;
    }

    public static boolean isPositiveNumber(String value) {
        return false;
    }

    public static boolean isFloatingPointNumber(String value) {
        if (StringChecker.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
//        return RealNumberHandle.isFloatingPoint(StringProcessor.toByteArray(value));
        return false;
    }

    public static boolean isScientificNumber(String value) {
        return false;
    }

    public static boolean isFloatDataType(String value) {
        if (StringChecker.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        if (value.contains("_") == true) {
            value = removeUnderscores(value);
        }
        value = value.toLowerCase();
        if (value.endsWith("f") == true) {
            value = value.substring(0, value.length() - 1);
            return isFloatingPointNumber(value) || isScientificNumber(value);
        }
        return false;
    }

    public static boolean isDoubleDataType(String value) {
        if (StringChecker.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        if (value.contains("_") == true) {
            value = removeUnderscores(value);
        }
        value = value.toLowerCase();
        if (value.endsWith("d") == true) {
            value = value.substring(0, value.length() - 1);
        }
        return isFloatDataType(value)
                || isFloatingPointNumber(value)
                || isScientificNumber(value);
    }

    public static boolean isIntegerDataType(String value) {

        return false;
    }

    public static boolean isNumberDataType(String value) {
        if (StringChecker.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        return false;
    }

    /**
     * public static boolean isLessThanOrEqualMax(String value, long max, long
     * min) { // if (StringProcessor.isNullOrWhiteSpaces(value) == true) { //
     * return false; // } // if (value.startsWith("-") == true) { // return
     * isGreatThanOrEqualMin(value); // } // char[] chrValue =
     * value.toCharArray(); // char[] chrMaxLong =
     * String.valueOf(Long.MAX_VALUE).toCharArray(); // if (chrValue.length <
     * chrMaxLong.length) { // return true; // } // for (int i = 0; i < chrMaxLong.length; i++) {
     * //            if (chrValue[i] > chrMaxLong[i]) { // return false; // } // } //
     * return true; // } // // public static boolean
     * isGreatThanOrEqualMin(String value, long max, long min) { // if
     * (StringProcessor.isNullOrWhiteSpaces(value) == true) { // return false;
     * // } // if (value.startsWith("-") == false) { // return
     * isLessThanOrEqualMax(value); // } // char[] chrValue =
     * value.toCharArray(); // char[] chrMinLong =
     * String.valueOf(Long.MIN_VALUE).toCharArray(); // if (chrValue.length <
     * chrMinLong.length) { // return true; // } // for (int i = 0; i <
     * chrMinLong.length; i++) { // if (chrValue[i] < chrMinLong[i]) { // return
     * false; // } // } // return true; // }
     */
    private static class IntegerNumberHandle {

        private static boolean isCharNumber(byte number) {
            int checkNum = number - DIGITS[0];
            return (checkNum >= 0 && checkNum <= 9);
        }

        private static boolean isNumber(byte[] arr) {
            for (byte num : arr) {
                if (isCharNumber(num) == false) {
                    return false;
                }
            }
            return true;
        }

        private static int getNumberType(byte[] arr) {
            if (arr.length == 1) {
                if (isCharNumber(arr[0])) {
                    return 1;
                }
                return 0;
            }

            if (isNumber(arr)) {
                return 1;
            }

            byte firstChar = arr[0];
            if (firstChar == POSITIVE
                    || firstChar == NEGATIVE) {
                byte[] copy = new byte[arr.length - 1];
                int index = 0;
                for (int i = 1; i < arr.length; i++) {
                    copy[index++] = arr[i];
                }
                if (isNumber(copy)) {
//                    return PrefixHandle.getPrefixType(arr);
                }
            }

            return 0;
        }

    }

    private static class RealNumberHandle {

        private static int isFloatingPointForm(byte[] arr) {
            int pointIndex = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == FLOATING_POINT) {
                    if (pointIndex != -1) {
                        return -1;
                    }
                    pointIndex = i;
                }
            }
            return pointIndex;
        }

        private static int isScientificForm(byte[] arr) {
            int eIndex = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == SCIENTIFIC) {
                    if (eIndex != -1) {
                        return -1;
                    }
                    eIndex = i;
                }
            }
            return eIndex;
        }

        private static void isValidScientificForm(byte[] arr) {
            if (isScientificForm(arr) >= 0) {

            }
        }

        private static boolean isRealNumber(byte[] arr) {
//            int pointIndex = isFloatingPointForm(arr),
//                    charEIndex = isScientificForm(arr);
//            if (pointIndex >= 0 || charEIndex > pointIndex) {
//                byte firstChrAfterPoint = arr[pointIndex + 1];
//                if (pointIndex == 0) {
//                    return IntegerNumberHandle.isCharNumber(firstChrAfterPoint);
//                }
//
//                if (firstChrAfterPoint == SCIENTIFIC) {
//                    byte firstChrAfterEChr = arr[charEIndex + 1];
////                    if () {
////                    }
//                }
//
//                if (IntegerNumberHandle.isCharNumber(firstChrAfterPoint)) {
//
//                }
//            }
            return false;
        }

    }

    private static class PrefixHandle {

        private static int getPrefixType(byte prefix) {
            if (prefix == NEGATIVE) {
                return -1;
            }
            if (prefix == POSITIVE
                    || IntegerNumberHandle.isCharNumber(prefix)) {
                return 1;
            }
            return 0;
        }
    }

}
