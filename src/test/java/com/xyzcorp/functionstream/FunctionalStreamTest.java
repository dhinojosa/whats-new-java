package com.xyzcorp.functionstream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
                          return Stream.empty();
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

    @SuppressWarnings("SimplifyStreamApiCallChains")
    @Test
    void testTakeWhile() {
        Set<String> result = Stream
            .iterate(0, integer -> integer + 1)
            .takeWhile(i -> i < 100)
            .map(String::valueOf)
            .collect(Collectors.toSet());
        assertThat(result).isEqualTo(Set.of("88",
            "89", "90", "91", "92", "93", "94", "95", "96", "97", "10", "98",
            "11", "99", "12", "13", "14", "15", "16", "17", "18", "19", "0",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "20", "21", "22",
            "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33",
            "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44",
            "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55",
            "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66",
            "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77",
            "78", "79", "80", "81", "82", "83", "84", "85", "86", "87"));
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
