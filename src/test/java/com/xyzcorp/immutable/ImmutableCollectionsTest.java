package com.xyzcorp.immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImmutableCollectionsTest {
    @Test
    void testUnmodifiableIsNotImmutable() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        List<String> newReferenceToStringList = Collections.unmodifiableList(stringList);
        stringList.add("d"); //Can I sneak this in
        assertThat(newReferenceToStringList).containsExactly("a", "b", "c", "d");
    }

    @Test
    void testImmutableCollectionsTrick() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList = Collections.unmodifiableList(stringList); //changed reference
    }

    @SuppressWarnings("DataFlowIssue")
    @Test
    void testNewImmutableList() {
        List<String> list = List.of("a", "b", "c");
        assertThatThrownBy(() -> list.add("d"))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Immutable Set is still a set and should not expect anything different")
    void testNewImmutableSet() {
        Set<String> set = Set.of("a", "b", "c");
        assertThat(set)
            .as("Set should contain the same elements")
            .contains("a", "b", "c")
            .isInstanceOf(Set.class);
    }

    @Test
    void testNewImmutableMap() {
        Map<Integer, String> map = Map.of(1, "One", 2, "Two", 3, "Three");
        assertThatThrownBy(() -> map.put(4, "Four")).isInstanceOf(java.lang.UnsupportedOperationException.class);
    }

    @Test
    void testNewImmutableMapWithEntries() {
        Map<Integer, String> integerStringMap = Map.ofEntries(entry(1, "One")
            , entry(2, "Two"), entry(3, "Three"));

        String result = integerStringMap.getOrDefault(2, "Not Found");
        assertThat(result).isEqualTo("Two");
    }
}
