package com.xyzcorp.immutablecollections;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ImmutableCollectionsTest {

    @Test
    void testUsingImmutableList() {
        List<Integer> integerList = List.of(40, 100, 340);
    }


    @Test
    void testUsingImmutableListWithMutableObjects() {
        List<Integer> integerList = List.of(40, 100, 340);
    }


    @Test
    void testSet() {
        Set<Integer> set = Set.of(40, 10);
    }

    @Test
    void testMap() {
        Map<Integer, String> integerStringMap = Map.of(1, "One", 2, "Two", 3,
            "Three", 4, "Four");
        Map<Integer, String> integerStringMap1 = Map.ofEntries(entry(1,
            "One"), entry(2, "Two"));

        integerStringMap.get(1);
        assertThat(integerStringMap.get(5)).isNull();
    }
}
