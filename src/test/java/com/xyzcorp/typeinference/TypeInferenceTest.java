package com.xyzcorp.typeinference;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class TypeInferenceTest {


    @Test
    void testNonDenotableObject() {
        var o = new Object() {
            String name = "Hello";
            LocalDate birthdate = LocalDate.of(2021, 10, 19);
        };

        System.out.println(o.name);
    }

    @Test
    void testStreamWithNonDenotableType() {
        List.of(1,2,3,4,5).stream().map(x -> {
            //create an object....
            return new Object() {
               int previous = x -1;
               int next = x + 1;
               int current = x;
               int negative = -x;
             };
        }).forEach(o -> System.out.printf("Previous: %d, " +
            "Current: %d", o.previous, o.current));
    }
}
