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
package com.noproject.data.processor;

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

//    private static final String BASIC_NUMBER = "^(\\+|\\-)?[0-9]{1,}$";
    private static final byte UNDERSCORE = 0x005F;// '_'

    private static final byte POSITIVE = 0x002B;// '+'

    private static final byte NEGATIVE = 0x002D;// '-'

    private static final byte FLOATING_POINT = 0x002E;// '.'

    private static final byte SCIENTIFIC = 0x0065;// 'e'

    private static final byte FLOAT_SIGN = 0x0066;// 'f'

    private static final byte DOUBLE_SIGN = 0x0064;// 'd'

    private static final byte[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static byte[] toByteArray(String value) {
        if (StringProcessor.isNullOrHasWhiteSpace(value)) {
            return new byte[]{};
        }
        final char[] charArrOfValue = value.toCharArray();
        final byte[] result = new byte[charArrOfValue.length];

        for (int i = 0; i < charArrOfValue.length; i++) {
            result[i] = (byte) charArrOfValue[i];
        }
        return result;
    }

    public static boolean isValidUnderscores(String value) {
        final byte[] byteArr = toByteArray(value);

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

    protected static String cleanUnderscores(String value) {
        if (isValidUnderscores(value) == false) {
            return StringProcessor.EMTPY_VALUE;
        }

        final byte[] byteArr = toByteArray(value);
        int countUnderscore = 0;
        for (int i = 0; i < byteArr.length; i++) {
            if (byteArr[i] == UNDERSCORE) {
                countUnderscore++;
            }
        }

        if (countUnderscore > 0) {
            final byte[] newArr = new byte[byteArr.length - countUnderscore];
            int newArrIndex = 0;
            for (int i = 0; i < byteArr.length; i++) {
                if (byteArr[i] != UNDERSCORE) {
                    newArr[newArrIndex++] = byteArr[i];
                }
            }
            return new String(newArr);
        }
        return value;
    }

    public static boolean isNumberCharacter(char number) {
        final int index = number - '0';
        if (index >= 0 && index <= 9) {
            return number == DIGITS[index];
        }
        return false;
    }

    public static boolean isBasicNumber(String value) {
        final byte[] byteArr = toByteArray(cleanUnderscores(value));
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
        final byte[] byteArr = toByteArray(value);

        if (byteArr.length > 0 && byteArr[0] == NEGATIVE) {
            final byte[] newArr = new byte[byteArr.length - 1];
            for (int i = 1; i < byteArr.length; i++) {
                newArr[i - 1] = byteArr[i];
            }
            return isBasicNumber(new String(newArr));
        }
        return false;
    }

    private static boolean isPositiveNumber(String value) {
        final byte[] byteArr = toByteArray(value);

        return isBasicNumber(value) == true
                && isNegativeNumber(value) == false;
    }

    public static boolean isFloatingPointNumber(String value) {
        if (StringProcessor.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        //identification sign
        final String sign = ".";
        if (value.contains(sign) == false) {
            return false;
        }

        final int indexOfSign = value.indexOf(sign);
        final String left = value.substring(0, indexOfSign);
        final StringBuilder right = new StringBuilder(
                value.substring(indexOfSign + sign.length())
                        .toLowerCase()
        );

        if (left.isEmpty() && right.isEmpty()) {
            return false;
        }

        boolean isValidLeft = left.isEmpty() ? true : isBasicNumber(left);
        boolean isValidRight = right.isEmpty();

        if (right.isEmpty() == false) {

            isValidRight = isScientificNumber(right.toString())
                    || (isPositiveNumber(right.toString()) == true && right.charAt(0) != '+');
        }

        return isValidLeft == true && isValidRight == true;
    }

    public static boolean isScientificNumber(String value) {
        value = value.toLowerCase();
        if (value.contains("e") == true) {
            String[] splitNum = value.split("e");
            if (splitNum.length == 2) {
                String left = splitNum[1];
                if (isFloatingPointNumber(left) == false) {
                    return isNegativeNumber(left)
                            || isPositiveNumber(left);
                }
            }
        }
        return false;
    }

    public static boolean isFloatDataType(String value) {
        if (StringProcessor.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        if (value.contains("_") == true) {
            value = cleanUnderscores(value);
        }
        value = value.toLowerCase();
        if (value.endsWith("f") == true) {
            value = value.substring(0, value.length() - 1);
            return isFloatingPointNumber(value) || isScientificNumber(value);
        }
        return false;
    }

    public static boolean isDoubleDataType(String value) {
        if (StringProcessor.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        if (value.contains("_") == true) {
            value = cleanUnderscores(value);
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
        if (StringProcessor.isNullOrHasWhiteSpace(value) == true) {
            return false;
        }
        return false;
    }

//    public static boolean isLessThanOrEqualMax(String value, long max, long min) {
//        if (StringProcessor.isNullOrWhiteSpaces(value) == true) {
//            return false;
//        }
//        if (value.startsWith("-") == true) {
//            return isGreatThanOrEqualMin(value);
//        }
//        char[] chrValue = value.toCharArray();
//        char[] chrMaxLong = String.valueOf(Long.MAX_VALUE).toCharArray();
//        if (chrValue.length < chrMaxLong.length) {
//            return true;
//        }
//        for (int i = 0; i < chrMaxLong.length; i++) {
//            if (chrValue[i] > chrMaxLong[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isGreatThanOrEqualMin(String value, long max, long min) {
//        if (StringProcessor.isNullOrWhiteSpaces(value) == true) {
//            return false;
//        }
//        if (value.startsWith("-") == false) {
//            return isLessThanOrEqualMax(value);
//        }
//        char[] chrValue = value.toCharArray();
//        char[] chrMinLong = String.valueOf(Long.MIN_VALUE).toCharArray();
//        if (chrValue.length < chrMinLong.length) {
//            return true;
//        }
//        for (int i = 0; i < chrMinLong.length; i++) {
//            if (chrValue[i] < chrMinLong[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
}
