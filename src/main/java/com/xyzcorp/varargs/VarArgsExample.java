package com.xyzcorp.varargs;

import java.util.Arrays;

public class VarArgsExample {

    public static void doSomething(int a, int b, int... rest) {
        System.out.println(Arrays.asList(rest));
    }
    public static void main(String... args) {
       doSomething(10, 340, 400, 500, 100, 300, 100);
       for (int i = 0; i < 100; i++) {
         System.err.println(i);
       }
    }
}
