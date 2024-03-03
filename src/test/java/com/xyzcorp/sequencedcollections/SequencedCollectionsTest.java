package com.xyzcorp.sequencedcollections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.SequencedCollection;

public class SequencedCollectionsTest {

    private String states = """
        Alaska
        Alabama
        Arkansas
        American Samoa
        Arizona
        California
        Colorado
        Connecticut
        District\s
        of Columbia
        Delaware
        Florida
        Georgia
        Guam
        Hawaii
        Iowa
        Idaho
        Illinois
        Indiana
        Kansas
        Kentucky
        Louisiana
        Massachusetts
        Maryland
        Maine
        Michigan
        Minnesota
        Missouri
        Mississippi
        Montana
        North Carolina
        North Dakota
        Nebraska
        New Hampshire
        New Jersey
        New Mexico
        Nevada
        New York
        Ohio
        Oklahoma
        Oregon
        Pennsylvania
        Puerto Rico
        Rhode Island
        South Carolina
        South Dakota
        Tennessee
        Texas
        Utah
        Virginia
        Virgin Islands
        Vermont
        Washington
        Wisconsin
        West Virginia
        Wyoming""";

    @Test
    void testGetFirstFromList() {
        SequencedCollection<String> sequencedCollection =
            Arrays.asList(states.split("\n"));
        String first = sequencedCollection.getFirst();
        Assertions.assertThat(first).isEqualTo("Alaska");
    }

    @Test
    void testFirstFromLinkedHashMap() {
        LinkedHashMap<String, String> capitals = LinkedHashMap.newLinkedHashMap(10);
        capitals.put("Texas", "Austin");
        capitals.put("Arizona", "Phoenix");
        capitals.put("New Mexico", "Santa Fe");
        SequencedCollection<String> sequencedCollection = capitals.sequencedKeySet();
        String first = sequencedCollection.getFirst();
        Assertions.assertThat(first).isEqualTo("Texas");
    }
}
