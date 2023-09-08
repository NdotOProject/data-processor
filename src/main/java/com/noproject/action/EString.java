/*
xử lí string có syntax ${} của groovy
nhưng không phải là gstring của groovy


 */
package com.noproject.action;

import com.noproject.checker.StringChecker;
import com.noproject.exception.ExpressionFormatException;
import java.util.Objects;

/**
 *
 * xoay quanh việc xử lí các đoạn string có chứa ${}
 *
 * thay các string có dạng ${string} thành các giá trị được truyền vào thông qua
 * các method setValue
 *
 * @author
 */
public class EString {

    private final StringEditor value;

    EString(StringEditor se) {
        value = se;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    // tìm và trả về expression có tên được chỉ định.
    public Expression findExpression(String name) {
        if (StringChecker.isNullOrNoContent(name)) {
            throw new ExpressionFormatException("");
        }

        Expression e = Expression.create(name);

        if (value.contains(e.toString())) {
            return e;
        }

        return null;
    }

    // tìm và trả về expression đầu tiên tìm được
    // và bắt đầu từ vị trí chỉ định.
    public Expression findExpression(int fromIndex) {
        if (fromIndex < 0) {
            throw new StringIndexOutOfBoundsException(fromIndex);
        }

        String prefix = Expression.getPrefix(),
                suffix = Expression.getSuffix();

        int start = value.indexOf(prefix, fromIndex);
        int end = value.indexOf(suffix, start);

        if (start == -1 || end == -1) {
            return null;
        }

        String expr = value.substring(start, end + suffix.length());
        return Expression.cast(expr);
    }

    // tìm và trả về expression đầu tiên tìm được.
    public Expression findExpression() {
        return findExpression(0);
    }

    // set value cho expression được chị định.
    public void setValue(Expression expr, String str) {
        expr = Objects.requireNonNull(expr);
        str = Objects.requireNonNull(str);
        value.change(expr.toString(), str);
    }

    // set value cho string có dạng(format) của Expression
    public void setValue(String expr, String str) {
        setValue(Expression.cast(expr), str);
    }

    // set value cho expression tìm thấy đầu tiên.
    public void setValue(String str) {
        String expr = findExpression().toString();
        setValue(expr, str);
    }

    /*
    tạo expression (create)
    chuyển string thành expression (cast)
    lấy đoạn string nằm trong ${} (getName)
     */
    public static class Expression {

        private static final byte DOLLAR_SIGN = '$';
        private static final byte OPENING_CURLY_BRACKET = '{';
        private static final byte CLOSING_CURLY_BRACKET = '}';

        private final String value;

        private Expression(String expr) {
            value = expr;
        }

        @Override
        public String toString() {
            return value;
        }

        public static Expression cast(String expr) {
            requireContent(expr);

            if (expr.startsWith(getPrefix())
                    && expr.endsWith(getSuffix())) {
                return new Expression(expr);
            }
            return null;
        }

        public static Expression create(String name) {
            requireContent(name);

            StringBuilder sb = new StringBuilder();
            sb.append(getPrefix());
            sb.append(name);
            sb.append(getSuffix());

            return new Expression(sb.toString());
        }

        public static String getName(String expr) {
            requireContent(expr);

            String prefix = getPrefix(), suffix = getSuffix();

            if (!expr.startsWith(prefix)
                    && !expr.endsWith(suffix)) {
                return null;
            }

            return expr.substring(prefix.length(),
                    expr.length() - suffix.length());
        }

        public static String getName(Expression expr) {
            String message = "";
            expr = Objects.requireNonNull(expr, message);
            return getName(expr.toString());
        }

        public String getName() {
            return getName(value);
        }

        private static void requireContent(String str) {
            if (StringChecker.isNullOrNoContent(str)) {
                String message = "";
                throw new ExpressionFormatException(message);
            }
        }

        private static String getPrefix() {
            return Character.toString(DOLLAR_SIGN)
                    .concat(Character.toString(OPENING_CURLY_BRACKET));
        }

        private static String getSuffix() {
            return Character.toString(CLOSING_CURLY_BRACKET);
        }

    }

}
