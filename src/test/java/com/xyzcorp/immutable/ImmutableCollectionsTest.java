package com.xyzcorp.immutable;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class ImmutableCollectionsTest {
    @Test
    void testUnmodifiableList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
//        unmodifiableList.add(30);
        list.add(30);
    }

    @Test
    void testImmutableList() {
        List<Integer> integers = List.of(1, 2, 2, 3, 4);
        integers.get(3);
    }

    @Test
    void testImmutableListWithStream() {
        List<Integer> integers =
            List.of(1, 2, 2, 3, 4);
        Stream<String> stream =
            integers.stream().map(i -> String.valueOf(i + 2));
    }

    @Test
    void testImmutableSet() {
        Set<Integer> integerSet = Set.of(4, 1, 2, 3, 4);
    }

    @Test
    void testImmutableMap() {
        Map<String, Integer> map = Map.of("One", 1, "Two", 2);
    }

    @Test
    void testImmutableMapWithEntries() {
        Map<String, Integer> stringIntegerMap =
            Map.ofEntries(entry("One", 1),
                entry("Two", 2), entry("Three", 3));

        Integer result = Optional
            .ofNullable(stringIntegerMap.get("Two"))
            .map(x -> x + 10)
            .orElse(-1);

        System.out.println(result);

        Integer result2 = Optional
            .ofNullable(stringIntegerMap.get("Four"))
            .map(x -> x + 10)
            .orElse(-1);

        System.out.println(result2);
    }
}
