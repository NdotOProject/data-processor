/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.noproject.test;

/**
 *
 * @author DELL
 */
@FunctionalInterface
public interface PerformanceTest {

    void excute();

    public static void showRuntime(PerformanceTest action) {
        long start, end;

        System.out.println("starting...");
        System.out.println(start = System.currentTimeMillis());

        action.excute();

        System.out.println(end = System.currentTimeMillis());
        System.out.println("finished.");
        System.out.println("runtime=" + (end - start));
    }

}
