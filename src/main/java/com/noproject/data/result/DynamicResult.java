/*
 * 
 */
package com.noproject.data.result;

public final class DynamicResult<L, R> {

    private L left, leftDefault;
    private R right, rightDefault;

    public DynamicResult() {
    }

    public DynamicResult(L defaultLeftValue, R defaultRightValue) {
        if (defaultLeftValue == null) {
            throw new DataError("Giá trị trái không hợp lệ.");
        }

        if (defaultRightValue == null) {
            throw new DataError("Giá trị phải không hợp lệ.");
        }

        this.leftDefault = defaultLeftValue;
        this.rightDefault = defaultRightValue;
    }

    public void set(L valueForLeft, R valueForRight) {
        left = valueForLeft;
        right = valueForRight;
    }

    public Data request() {
        Data result = new Data();
        if (left != null || leftDefault != null) {
            return left != null
                    ? result.set(left)
                    : result.set(leftDefault);
        }
        if (right != null || rightDefault != null) {
            return right != null
                    ? result.set(right)
                    : result.set(rightDefault);
        }
        return result.set(null);
    }

    public Data require(Location of) {
        return require(of, leftDefault, rightDefault);
    }

    public Data require(Location of, L defLeftVal, R defRightVal) {
        if (defLeftVal == null) {
            throw new DataError("Bạn không có giá trị mặc định cho left.");
        }
        if (defRightVal == null) {
            throw new DataError("Bạn không có giá trị mặc định cho right.");
        }

        Data result = new Data();
        switch (of) {
            case OUT_LEFT:
            case LEFT:
                if (left == null) {
                    if (leftDefault != null) {
                        return result.set(leftDefault);
                    }
                    return result.set(defLeftVal);
                }
                return result.set(left);

            case OUT_RIGHT:
            case RIGHT:
                if (right == null) {
                    if (rightDefault != null) {
                        return result.set(rightDefault);
                    }
                    return result.set(defRightVal);
                }
                return result.set(right);

            default:
                return require(Location.LEFT, defLeftVal, defRightVal);
        }
    }

}
