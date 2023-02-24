package com.xyzcorp.functionstream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectorsTest {
    @SuppressWarnings("SimplifyStreamApiCallChains")
    @Test
    void testToUnmodifiableList() {
        List<Integer> evenList =
            List.of(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableList());
        assertThat(evenList).isEqualTo(List.of(2, 4));
    }

    @SuppressWarnings("SimplifyStreamApiCallChains")
    void testToUnmodifiableSet() {
        Set<Integer> evenSet =
            List.of(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableSet());
        assertThat(evenSet).isEqualTo(Set.of(2,4));
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
}
