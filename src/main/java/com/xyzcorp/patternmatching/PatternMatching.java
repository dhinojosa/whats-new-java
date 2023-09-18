package com.xyzcorp.patternmatching;

public class PatternMatching {

    public static String matchOldWay(Object o) {
        if (o instanceof String) {
            String s = (String) o;
            if ("Hello".equals(s)) return "Hello to you";
            else return "A String";
        } else if (o instanceof Integer) {
            Integer i = (Integer) o;
            return String.valueOf(i + 30);
        }
        return "Nothing";
    }

    public static String match(Object o) {
       if (o instanceof String s) {
           if ("Hello".equals(s)) return "Hello to you";
           else return "A String";
       } else if (o instanceof Integer i) {
           return String.valueOf(i + 30);
       }
       return "Nothing";
    }


    public static String compoundMatch(Object o) {
        if (o instanceof String s && !s.isEmpty()) {
            return "non empty string";
        }
        return "not a string or is empty";
    }

    // Preview
    // public static void matchString(String s) {
    //     switch (s) {
    //         case null         -> System.out.println("Oops");
    //         case "Foo", "Bar" -> System.out.println("Great");
    //         default           -> System.out.println("Ok");
    //     }
    // }
    
    // Preview
    // static String formatterPatternSwitch(Object obj) {
    //     return switch (obj) {
    //         case Integer i -> String.format("int %d", i);
    //         case Long l    -> String.format("long %d", l);
    //         case Double d  -> String.format("double %f", d);
    //         case String s  -> String.format("String %s", s);
    //         default        -> obj.toString();
    // }
}
