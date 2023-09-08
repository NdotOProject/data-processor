/*
 * 
 */
package com.noproject.result;

/**
 *
 *
 */
public final class Data {

    private static final String BYTE_CLASS_NAME = Byte.class.getName();
    private static final String SHORT_CLASS_NAME = Short.class.getName();
    private static final String INT_CLASS_NAME = Integer.class.getName();
    private static final String LONG_CLASS_NAME = Long.class.getName();
    private static final String FLOAT_CLASS_NAME = Float.class.getName();
    private static final String DOUBLE_CLASS_NAME = Double.class.getName();
    private static final String CHAR_CLASS_NAME = Character.class.getName();
    private static final String BOOLEAN_CLASS_NAME = Boolean.class.getName();
    private static final String STRING_CLASS_NAME = String.class.getName();
    private static final String OBJECT_CLASS_NAME = Object.class.getName();

    Data() {
    }

    private Object data;
    private Class dataClass;

    Data set(Object value) {
        data = value;
        dataClass = value != null ? value.getClass() : null;
        return this;
    }

    public <T> Class<T> getDataType() {
        return dataClass;
    }

    public <T> T get() {
        return get(getDataType());
    }

    public <T> T get(Class<T> c) {
        if (data == null || c == null) {
            return null;
        }

        String typeName = dataClass.getName();

        if (c.isPrimitive() == true) {
            String primitiveType = castPrimitiveToObject(c);
            if (typeName.equals(primitiveType)) {
                return (T) data;
            }
        }

        String inputDataType = c.getName();

        if (inputDataType.equals(STRING_CLASS_NAME)) {
            return (T) String.valueOf(data);
        }

        if (inputDataType.equals(OBJECT_CLASS_NAME)
                || inputDataType.equals(typeName)) {
            return (T) data;
        }

        throw new ClassCastException("The requested data type does"
                + " not match the data type of the stored value.");
    }

    private String castPrimitiveToObject(Class c) {
        if (c == null) {
            return null;
        }

        switch (c.getName()) {
            case "byte":
                return BYTE_CLASS_NAME;

            case "short":
                return SHORT_CLASS_NAME;

            case "int":
                return INT_CLASS_NAME;

            case "long":
                return LONG_CLASS_NAME;

            case "float":
                return FLOAT_CLASS_NAME;

            case "double":
                return DOUBLE_CLASS_NAME;

            case "char":
                return CHAR_CLASS_NAME;

            case "boolean":
                return BOOLEAN_CLASS_NAME;

            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : null;
    }

}
