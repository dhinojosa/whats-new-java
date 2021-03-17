package com.xyzcorp.nondenotabletype;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NonDenotableTypeTest {

    @Test
    void testingFunctionalProgrammingWithRecord() {
        record Tuple3<A, B, C>(A a, B b, C c) {
        }
        Stream<Tuple3<Integer, String, Double>> tuple3Stream = Stream.of(1, 2
            , 3, 4).map(i -> new Tuple3<>(i + 1, "Hello" + i, i + 20.0));

        Stream<String> stringStream = tuple3Stream.map(Tuple3::b);
        System.out.println(stringStream.collect(Collectors.joining()));
    }

    @Test
    void testNonDenotableType() {
        var productInfo = new Object() {
            String name = "Apple";
            int total = 30;
        };

        System.out.println(productInfo.name);
        System.out.println(productInfo.total);
    }

    @Test
    void testNonDenotableTypeInAStream() {
        System.out.println(
            List.of(3, 1, 5, 10, 100, 200)
                .stream()
                               .map(i -> {
                                   var characteristic = new Object() {
                                       int previous = i - 1;
                                       int curr = i;
                                       int next = i + 1;
                                       int abs = Math.abs(i);
                                   };
                                   return characteristic;
                               }).filter(c -> c.previous % 2 == 0).collect(Collectors.toList()));
    }
}
