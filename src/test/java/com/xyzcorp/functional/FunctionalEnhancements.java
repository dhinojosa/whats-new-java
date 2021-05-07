package com.xyzcorp.functional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalEnhancements {

    @Test
    void testMap() {
        List<Integer> integers =
            Stream.of(1, 2, 3, 4).map(x -> x * 2).collect(Collectors.toList());
    }

    @Test
    void testFlatMap() {
        Stream<Integer> integerStream =
            Stream.of(1, 2, 3, 4).flatMap(x -> Stream.of(-x, x, x + 1));
        System.out.println(integerStream);
    }

    @Test
    void testWordCount() {
        Stream<String> stringStream = Stream.of("I see trees of green", "Red " +
                "roses too", "I see them bloom",
            "For me and you", "And I think to myself", "What a wonderful " +
                "world");


        Map<Character, Integer> wordCharCount =
            stringStream
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .peek(s -> debug(s, "S1:"))
                .map(String::toLowerCase)
                .peek(s -> debug(s, "S2:"))
                .collect(Collectors.groupingBy(w -> w.charAt(0)))
                .entrySet()
                .stream()
                .map(e -> Map.entry(e.getKey(), e.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(wordCharCount);


    }

    private void debug(String s, String tag) {
        System.out.println(tag + s);
    }

    @Test
    void testHowToFilterUsingOptionsAndFlatMap() {

        Stream<Integer> integerStream =
            Stream.of(100, 2, 5, -1, 0, 4, 10).flatMap(x -> {
            try {
                return Stream.of(1000 / x);
            } catch (ArithmeticException ae) {
                return Stream.empty();
            }
        });

        integerStream.forEach(System.out::println);
    }

    @Test
    void testOptionalStream() {
        Stream<Optional<Integer>> optionalStream = Stream.of(Optional.of(3),
            Optional.of(4),
            Optional.<Integer>empty(), Optional.of(10));

        Stream<Integer> integerStream =
            optionalStream.flatMap(Optional::stream);

        integerStream.forEach(System.out::println);
    }
}
