package com.xyzcorp.scopedvalues.large;

import java.util.NoSuchElementException;

public class Repository {
    public Long persist() {
        printThreadAndKey("In Repository#persist()");
        return 50L;
    }

    public Employee find() {
        return ScopedValue.where(Application.KEY, "Buenos Dias").get(() -> {
            printThreadAndKey("In Repository#find()");
            return new Employee("James", "Gosling");
        });
    }

    private static void printThreadAndKey(String label) {
        try {
            System.out.format("%s: %s contains key \"%s\"\n", label, Thread.currentThread(), Application.KEY.get());
        } catch (NoSuchElementException e) {
            System.out.format("%s: %s has no key!\n", label, Thread.currentThread());
        }
    }
}
