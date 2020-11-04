package com.xyzcorp.collectors;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class NewCollectorsTest {
    @Test
    void testToUnmodifiableList() {
        List<Integer> evenList =
            List.of(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableList());
    }

    void testToUnmodifiableSet() {
        Set<Integer> evenSet =
            List.of(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Test
    void testToUnmodifiableMap() {
        Map<Integer, Double> map =
            IntStream.range(1, 5)
                     .boxed()
                     .collect(Collectors.toUnmodifiableMap(
                         i -> i,
                         i -> Math.pow(i, 3))
                     );
        System.out.println(map);
    }

    @Test
    void testTeeing() {
        double average = Stream.of(1, 4, 2, 7, 4, 6, 5)
                               .collect(teeing(
                                   summingDouble(i -> i),
                                   counting(),
                                   (sum, n) -> sum / n));
        System.out.println(average);
    }
}
