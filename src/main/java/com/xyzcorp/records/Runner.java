package com.xyzcorp.records;

public class Runner {
    public static void main(String[] args) {
        System.out.println(ProcessHandle.current().pid());
    }
}
