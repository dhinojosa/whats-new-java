package com.xyzcorp.processhandles;

public class ProcessHandlesApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(ProcessHandle.current().pid());
        Thread.sleep(50000);
    }
}
