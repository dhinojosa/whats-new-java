package com.xyzcorp.typeinference;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class FunctionsTest {
    @Test
    void testMapGet() {
        Map<Integer, String> mapNumberAndName =
            Map.ofEntries(
                entry(1, "One"),
                entry(2, "Two"));

        Optional<String> s =
            Optional.ofNullable(mapNumberAndName.get(1))
                    .flatMap(x -> Optional.ofNullable(mapNumberAndName.get(5)).map(y -> x + y));

        System.out.println(s);
    }

    @Test
    void testHandlingError() {
        Stream.of(100, 25, 5, 2, 0, 10, 20)
              .flatMap(x -> {
                  try {
                      return Stream.of(100 / x);
                  } catch (ArithmeticException ae) {
                      return Stream.<Integer>empty();
                  }
              })
              .forEach(System.out::println);
    }

    @Test
    void testOptionalStream() {
        Stream<Optional<Integer>> soi = Stream.of(Optional.of(2),
            Optional.of(10), Optional.empty(), Optional.of(50));

        soi.flatMap(Optional::stream).forEach(System.out::println);
    }

    @Test
    void testStreamDebugging() {
        IntStream.range(0, 100)
                 .map(x -> x * 3)
                 .filter(x -> x % 2 != 0)
                 .forEach(System.out::println);
    }

    @Test
    void testOtherFunction() {
        Stream.iterate(0, integer -> integer < 40, integer -> integer + 3);
    }

    @Test
    void testTeeing() {
        Double average =
            Stream.of(100, 100, 90, 100, 40, 80, 60, 80, 88)
                  .collect(Collectors.teeing(Collectors.summingDouble(x -> x),
                      Collectors.counting(), (sum, count) -> sum / count));
        System.out.println(average);
    }
}
