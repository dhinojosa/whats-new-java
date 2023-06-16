package com.xyzcorp.immutable;

import org.junit.jupiter.api.Test;

import java.util.*;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

public class ImmutableCollectionsTest {
    @Test
    void testUnmodifiableIsNotImmutable() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        List<String> newReferenceToStringList = Collections.unmodifiableList(stringList);
        System.out.println(newReferenceToStringList);
        stringList.add("d"); //Can I sneak this in
        System.out.println(newReferenceToStringList);
    }

    @Test
    void testImmutableCollectionsTrick() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList = Collections.unmodifiableList(stringList); //changed reference
    }

    @Test
    void testNewImmutableList() {
        List<String> list = List.of("a", "b", "c");
        list.add("d");
    }

    @Test
    void testNewImmutableSet() {
        Set<String> set = Set.of("a", "b", "c");
    }

    @Test
    void testNewImmutableMap() {
        Map<Integer, String> map = Map.of(1, "One", 2, "Two", 3, "Three");
        //map.put(4, "Four"); No it is immutable
    }

    @Test
    void testNewImmutableMapWithEntries() {
        Map<Integer, String> integerStringMap = Map.ofEntries(entry(1, "One")
            , entry(2, "Two"), entry(3, "Three"));

        String result = integerStringMap.getOrDefault(2, "Not Found");
        assertThat(result).isEqualTo("Two");
    }
}
