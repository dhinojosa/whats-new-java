package com.xyzcorp.functionstream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalStreamTest {
    @Test
    void testMapGet() {
        Map<Integer, String> integerStringMap = Map.of(1, "One", 2, "Two");
        String s = integerStringMap.get(3);
        System.out.println(Optional.ofNullable(s));
    }

    @Test
    void testOptionalsWithPossibleException() {
        System.out.println(
            Stream.of(1, 50, 0, 20, 10)
                  .flatMap(i -> {
                      try {
                          return Stream.of(100 / i);
                      } catch (ArithmeticException ae) {
                          return Stream.<Integer>empty();
                      }
                  })
                  .collect(Collectors.toList()));
    }

    @Test
    void testOptionalStream() {
        Stream<Optional<Integer>> os =
            Stream.of(Optional.of(2), Optional.empty(), Optional.of(30));
        Stream<Integer> integerStream = os.flatMap(Optional::stream);
        System.out.println(integerStream.collect(Collectors.toList()));
    }

    @Test
    void testTakeWhile() {
        Stream.iterate(0, integer -> integer + 1)
              .takeWhile(i -> i < 100)
              .collect(Collectors.toSet());
    }

    @Test
    void testDropWhile() {
        List<Integer> result =
            Stream.iterate(0, integer -> integer + 1)
                  .dropWhile(i -> i < 5)
                  .filter(i -> i % 2 == 0)
                  .takeWhile(i -> i < 50)
                  .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    void testThreeArgIterable() {
        Stream<Integer> stream = Stream.iterate(0, i -> i < 5, i -> i + 1);
        System.out.println(stream.collect(Collectors.toList()));
    }

    @Test
    void testTeeing() {
        Double result =
            Stream.of(100, 100, 90, 50, 40, 80, 90, 100)
                             .collect(Collectors.teeing(
                                 Collectors.summingDouble(value -> value),
                                 Collectors.counting(),
                                 (sum, count) -> sum / count));
        System.out.println(result);
    }
}
