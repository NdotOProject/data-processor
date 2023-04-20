/*
 * 
 */
package com.noproject.data.result;

public final class MultipleResult<OL, L, M, R, OR> {

    private static final int OUT_LEFT_INDEX = 0;
    private static final int LEFT_INDEX = 1;
    private static final int MIDDLE_INDEX = 2;
    private static final int RIGHT_INDEX = 3;
    private static final int OUT_RIGHT_INDEX = 4;
    private static final int COUNT_ELEMENTS = 5;

    private final Object[] listResults;

    public MultipleResult() {
        listResults = new Object[COUNT_ELEMENTS];
    }

    public boolean change(int index, Object value) {
        Object currentValue = listResults[index];
        if (currentValue != null) {
            if (currentValue.equals(value)) {
                return true;
            }
            String dataClassName = currentValue.getClass().getName();
            String inputClassName = value.getClass().getName();
            String objectClassName = Object.class.getName();
            if (inputClassName.equals(dataClassName)
                    || inputClassName.equals(objectClassName)) {
                listResults[index] = value;
                return listResults[index] != currentValue;
            }
            throw new DataError("Không thể thay thế"
                    + " giá trị có kiểu dữ liệu khác với"
                    + " kiểu dữ liệu ban đầu.");
        }
        listResults[index] = value;
        return listResults[index] != null;
    }

    public boolean save(Location of, Object value) {
        switch (of) {
            case OUT_LEFT:
                return change(OUT_LEFT_INDEX, value);

            case LEFT:
                return change(LEFT_INDEX, value);

            case MIDDLE:
                return change(MIDDLE_INDEX, value);

            case RIGHT:
                return change(RIGHT_INDEX, value);

            case OUT_RIGHT:
                return change(OUT_RIGHT_INDEX, value);

            default:
                return false;
        }
    }

    public Data get(Location of) {
        Data result = new Data();
        switch (of) {
            case OUT_LEFT:
                return result.set(listResults[OUT_LEFT_INDEX]);

            case LEFT:
                return result.set(listResults[LEFT_INDEX]);

            case MIDDLE:
                return result.set(listResults[MIDDLE_INDEX]);

            case RIGHT:
                return result.set(listResults[RIGHT_INDEX]);

            case OUT_RIGHT:
                return result.set(listResults[OUT_RIGHT_INDEX]);

            default:
                return result.set(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < COUNT_ELEMENTS; i++) {
            sb.append(listResults[i]);
            if (i < COUNT_ELEMENTS - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
