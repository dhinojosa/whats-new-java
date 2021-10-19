package com.xyzcorp.collections;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.*;

public class CollectionsTest {

    @Test
    void testList() {
        List<Integer> integers = List.of(1, 2, 3, 4);
        List.copyOf(integers);
    }

    @Test
    void testSet() {
        Set.of(4, 1, 2);
    }

    @Test
    void testMap() {
        Map<String, Integer> countryCountMap = Map.of("Canada", 12, "Mexico", 19,
            "Costa Rica", 11);
        Map<String, Integer> anotherCountryMap = Map.ofEntries(entry(
            "Venezuela", 17), entry("Japan", 45));
        Set<Map.Entry<String, Integer>> entries = countryCountMap.entrySet();
    }
}
