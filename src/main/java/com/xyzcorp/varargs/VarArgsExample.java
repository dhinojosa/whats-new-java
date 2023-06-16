package com.xyzcorp.varargs;

public class VarArgsExample {

    public static void doSomething(int a, int b, int... rest) {
        int[] rest1 = rest;

    }
    public static void main(String... args) {
       doSomething(10, 340, 400, 500, 100, 300, 100);
    }
}
