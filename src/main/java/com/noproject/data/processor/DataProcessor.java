package com.noproject.data.processor;

import com.noproject.data.result.Data;
import com.noproject.data.result.Location;
import com.noproject.data.result.DynamicResult;
import com.noproject.data.result.MultipleResult;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public final class DataProcessor {

    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(0>>>32);
//        AtomicInteger
//        float f0 = 11.1e0__1f,// 1F
//                f1 = 1.f,// 1.F
//                f2 = 1.2f,// 1.2F 
//                f3 = 1e1f,// 1e2F, 1E2f, 1E2F
//                f4 = 1.1e-0f,// 1.e2F, 1.E2f, 1.E2F
//                f5 = 1.2e+2f;// 1.2e2F, 1.2E2f, 1.2E2F
//        System.out.println(f0);
        //%, *, -, +, / => math
//         d = 1.e1 - 1;
//        d = 1___11e0__1f;
//        d = 1___11e0__1d;
//        double d = 1_1.e00_1;
//        float f = -1e-1_1f;
//        int i = 1_1__1;
//        System.out.println((byte) '+');
//        
//        System.out.println(d);
//        System.out.println(f);
//        System.out.println(i);
// 2, byte 8,  10, short 16, int 32, long 64
//        System.out.println(Integer.MAX_VALUE);
        // 0 * 16 + 10 = 0 + 10 = 10
        // 10 * 16 + 11 = 160 + 11 = 171
//        System.out.println(Math.pow(2, 7));
//        System.out.println(Double.toString(d));
//        System.out.println(Integer.parseInt("7", 8));
//        System.out.println(0x80000000);
//        System.out.println(0x7fffffff);
//        System.out.println(2 * 8);
//        System.out.println(Math.pow(2, 8));
//        System.out.println(0x2 * 10);
//        System.out.println(0xA);
//        System.out.println(0xA * 10 +  0xb);
//        System.out.println(Integer.valueOf("99999999"));
//        String number = "10";
//        System.out.println(NumberProcessor.isNumber(number));
//test zone
//        List<Integer> list = new ArrayList<>();
//        list.forEach(in -> {
//        });
//
//        System.out.println(0xFFFFFFFFFL);
//        System.out.println();
//        System.out.println(1 | 2);
//        String binary[] = {
//            "0000", "0001", "0010", "0011", "0100", "0101",
//            "0110", "0111", "1000", "1001", "1010",
//            "1011", "1100", "1101", "1110", "1111"
//        };
//
//        System.out.println((char) 0x005F);
//
//        int un = Integer.parseInt("1011", 2);
//        System.out.println(un);
        // initializing the values of a and b
//        int a = 3; // 0+2+1 or 0011 in binary
//        int b = 6; // 4+2+0 or 0110 in binary
//
//        // bitwise or
//        int c = a | b;
//
//        // bitwise and
//        int d = a & b;
//
//        // bitwise xor
//        int e = a ^ b;
//
//        // bitwise not
//        int f = (~a & b) | (a & ~b);
//        int g = ~a & 0x0f;
//
//        System.out.println(" a= " + binary[a]);
//        System.out.println(" b= " + binary[b]);
//        System.out.println(" a|b= " + binary[c]);
//        System.out.println(" a&b= " + binary[d]);
//        System.out.println(" a^b= " + binary[e]);
//        System.out.println("~a & b|a&~b= " + binary[f]);
//        System.out.println("~a= " + binary[g]);
//        System.out.println(2*8);
//        System.out.println(2^8);
//        System.out.println("1.".split("\\.").length);
//        System.out.println(".1".split("\\.")[0].isEmpty());
//        System.out.println(12&11);
//        System.out.println(Float.valueOf(".11f"));
//        System.out.println(NumberProcessor.isFloatingPointNumber("1.1e-11"));
//        System.out.println(NumberProcessor.isFloatingPointNumber(".1e1"));
//        System.out.println(NumberProcessor.isBasicNumber("123"));
//        System.out.println(NumberProcessor.isFloatDataType("1__-_1.1e-1f"));
// ♪
//        String s = "\u0000";
//        s = "\uffff";
//        System.out.println(s.charAt(0));
//        System.out.println(Character.valueOf('\uFFFF'));
//        System.out.println((char)0xff);
//        String s = "0x005F";
        long start, end;
        start = System.currentTimeMillis();

//        System.out.println(NumberProcessor.isNumberCharacter('1'));
//        System.out.println(Arrays.toString(NumberProcessor.toByteArray(String.valueOf(Long.MAX_VALUE))));
//        System.out.println(NumberProcessor.isValidUnderscores(""));
//        System.out.println(Arrays.toString(NumberProcessor.toByteArray("1_123")));
//        System.out.println(NumberProcessor.isFloatingPointNumber("1_1.-1e-1_1"));
//        System.out.println(NumberProcessor.isScientificNumber("-1e-1_1"));
        end = System.currentTimeMillis();
        System.out.println(end - start + "ms");

    }
}
