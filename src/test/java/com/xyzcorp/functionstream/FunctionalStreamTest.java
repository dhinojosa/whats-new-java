package com.xyzcorp.functionstream;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalStreamTest {
    @Test
    void testOptionalStream() {
        Stream<Optional<Integer>> os = Stream.of(Optional.of(2), Optional.empty(), Optional.of(30));
        Stream<Integer> integerStream = os.flatMap(Optional::stream);
        System.out.println(integerStream.collect(Collectors.toList()));
    }

    @Test
    void testTakeWhile() {
        Stream.iterate(0, integer -> integer +1 ).takeWhile(i -> i < 100).collect(Collectors.toSet());
    }

    @Test
    void testDropWhile() {
        Stream.iterate(0, integer -> integer +1 ).dropWhile(i -> i < 5).takeWhile(i -> i < 50).collect(Collectors.toSet());
    }
}
