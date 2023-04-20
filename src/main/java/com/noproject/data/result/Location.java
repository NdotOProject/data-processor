/*
 * 
 */
package com.noproject.data.result;

/**
 *
 *
 */
public enum Location {

    /*
     *  Example : arr = {1, 2, 3, 4, 5}
     * -> 1 =  OUT_LEFT (Vị trí ngoài cùng bên trái.)
     * -> 2 = LEFT (Vị trí bên trái của MIDDLE.)
     * -> 3 = MIDDLE (Vị trí ở giữa, trung tâm.)
     * -> 4 = RIGHT (Vị trí bên phải của MIDDLE.)
     * -> 5 = OUT_RIGHT (Vị trí ngoài cùng bên phải.)
     */
    OUT_LEFT, LEFT, MIDDLE, RIGHT, OUT_RIGHT
}
