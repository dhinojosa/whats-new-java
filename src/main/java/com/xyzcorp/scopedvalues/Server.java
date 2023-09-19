package com.xyzcorp.scopedvalues;


import java.time.format.DateTimeFormatter;

public class Server {
    public static void processing() {
        System.out.println(Framework.rateScopedValue.get());
    }

    public static <A> void processingWith(A a) {
        System.out.println(Thread.currentThread());
        System.out.println(a);
    }

    public static String processDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        System.out.println(Thread.currentThread());
        return formatter.format(Framework.dateScopedValue.get());
    }

    static void calculatingRate() {
        System.out.println(Thread.currentThread());
        System.out.printf("We can make a complete run from here while getting %d",
            Framework.rateScopedValue.orElse(new Rate(0)).rate());
    }
}
