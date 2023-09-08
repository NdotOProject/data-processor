/*
 *
 */
package com.noproject.processor;

import com.noproject.checker.StringChecker;

/**
 *
 * @author
 */
public class BooleanProcessor {

    private BooleanProcessor() {
    }

    
    /**
     * Nếu value(String) có chứa 
     * 
     * @param value
     * @param defaultValue
     * @return 
     */
    public static boolean safelyCast(String value, boolean defaultValue) {
        if (StringChecker.isNullOrNoContent(value) == false) {
            value = value.toLowerCase();
            
            if (value.contains("true")) {
                return true;
            } else if (value.contains("false")) {
                return false;
            }
        }
        return defaultValue;
    }

    public static boolean safelyCast(int value, boolean defaultValue) {
        if (value <= 0) {
            return false;
        } else if (value > 0) {
            return true;
        }
        return defaultValue;
    }

    public static void main(String[] args) {
        System.out.println(BooleanProcessor.safelyCast("false", true));
    }
}
