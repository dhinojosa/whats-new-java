package com.xyzcorp.typeinference;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TypeInferenceTest {


    //fun add(a:Int, b:Int)
    public int add(int a, int b) {
        return a + b;
    }

    @Test
    void testTypeInference() {
        var result = add(10, 40);
        var myString = "Hello";
        myString.getBytes();
    }

    @Test
    void testEveryElementStatisticsWithNonDenotableTypes() {
        List.of(1, 2, 3, 4, 5).stream()
            .map(i -> new Object() {
                int negative = -i;
                int previous = i - 1;
                int current = i;
                int next = i + 1;
            })
            .forEach(o ->
                System.out.printf("prev: %d, curr: %d, next: %d, negative: " +
                    "%d\n", o.previous, o.current, o.next, o.negative));
    }
}
