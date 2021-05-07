package com.xyzcorp;

public class PatternMatching {
    public static String performPatternMatch(Object o) {
        if (o instanceof String s) {
            return  "This is a string of" + s;
        } else if (o instanceof Integer i) {
            return  "This is a integer of" + i;
        }
        return "Don't know";
    }

    public void compoundMatch(Object o) {
        if (o instanceof String s && !s.isEmpty()) {
            System.out.println("This is a non-empty string");
        }
    }
}
