/*
 */
package com.noproject.action;

import com.noproject.processor.StringProcessor;

/**
 * tất cả đầu vào(input) là null thì đều được đổi thành empty string("").
 *
 * xoay quanh việc chỉnh sửa đoạn string ban đầu.
 *
 * là cách tiếp cận khác của StringBuilder. (dùng string làm input thay vì các
 * index(int))
 *
 * @author
 */
public class StringEditor {

    private final StringBuilder value;
    private static final String EMPTY_STRING = "";

    public StringEditor(String str) {
        str = StringProcessor.getNonNullValue(str, EMPTY_STRING);

        value = new StringBuilder(str);
    }

    // 
    @Override
    public String toString() {
        return value.toString();
    }

    // append: nối thêm
    public StringEditor append(String str) {
        str = StringProcessor.getNonNullValue(str, EMPTY_STRING);

        value.append(str);

        return this;
    }

    public StringEditor append(StringBuffer sbuff) {
        return append(sbuff.toString());
    }

    public StringEditor append(StringBuilder sbuild) {
        return append(sbuild.toString());
    }

    public StringEditor append(CharSequence cs) {
        return append(cs.toString());
    }

    // insert: chèn
    // insert vào vị trí xác định
    public StringEditor insert(int offset, String str) {
        str = StringProcessor.getNonNullValue(str, EMPTY_STRING);

        value.insert(offset, str);

        return this;
    }

    // insert vào trước/sau một đoạn string.
    /*
    isInsertBefore = true => isert before str2
    isInsertBefore = flase => insert after str2
     */
    public StringEditor insert(String str,
            boolean isInsertBefore, String existString) {

        if (!contains(existString)) {
            String message = existString + " khong ton tai.";
            throw new NullPointerException(message);
        }

        int offset = value.indexOf(existString);

        if (isInsertBefore) {
            return insert(offset, str);
        } else {
            offset = offset + existString.length();
            return insert(offset, str);
        }
    }

    public StringEditor insertBefore(String str, String existStr) {
        return insert(str, true, existStr);
    }

    public StringEditor insertAfter(String str, String existStr) {
        return insert(str, false, existStr);
    }

    // change/convert: thay đổi
    // đổi một đoạn string chỉ định thành một đoạn string khác.
    public StringEditor change(String existStr, String toNewStr) {

        if (!contains(existStr)) {
            String message = existStr + " khong ton tai.";
            throw new NullPointerException(message);
        }

        int start = value.indexOf(existStr),
                end = start + existStr.length();

        toNewStr = StringProcessor.getNonNullValue(toNewStr, EMPTY_STRING);

        value.replace(start, end, toNewStr);
        return this;
    }

    public StringEditor change(String existStr, CharSequence toNewStr) {
        return change(existStr, toNewStr.toString());
    }

    // delete/remove: xóa
    public StringEditor delete(int start, int end) {
        value.delete(start, end);
        return this;
    }

    public StringEditor delete(int start) {
        return delete(start, value.length());
    }

    // xóa đoạn string được chỉ định.
    public StringEditor delete(String str) {

        if (!contains(str)) {
            String message = str + " khong ton tai.";
            throw new NullPointerException(message);
        }

        int start = value.indexOf(str),
                end = start + str.length();

        return delete(start, end);
    }

    public boolean contains(String str) {
        return toString().contains(str);
    }

    public String substring(int start, int end) {
        return value.substring(start, end);
    }

    public String[] split(String regex) {
        return split(regex, 0);
    }

    public String[] split(String regex, int limit) {
        return toString().split(regex, limit);
    }

    public int indexOf(String str, int fromIndex) {
        return value.indexOf(str, fromIndex);
    }

    public String toLowerCase() {
        return toString().toLowerCase();
    }

    public String toUpperCase() {
        return toString().toUpperCase();
    }

    public byte[] toByteArray() {
        return StringProcessor.toByteArray(toString());
    }

    public char[] toCharArray() {
        return StringProcessor.toCharArray(toString());
    }

    // create class EString
    public EString useExpressionString() {
        return new EString(this);
    }

}
