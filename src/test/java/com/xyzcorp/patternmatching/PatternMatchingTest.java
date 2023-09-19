package com.xyzcorp.patternmatching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PatternMatchingTest {
    @Test
    void testEnsureThatMatchWorks() {

        String result = PatternMatching.match("Hello");
        assertThat(result).isEqualTo("Hello to you");
    }

    @Test
    void testCompoundMatch() {
        String result = PatternMatching.compoundMatch("Hello");
        assertThat(result).isEqualTo("non empty string");
    }

    @Test
    void testStringMatch() {
        PatternMatching.matchString("Foo");
        PatternMatching.matchString("Bar");
        PatternMatching.matchString(null);
    }

    @Test
    void testStringMatchWithRecordMatching() {
        String result = PatternMatching.matchRecordPatterns(new Team("Seattle", "Mariners", 30, 12));
        assertThat(result).isEqualTo("Team Mariners from Seattle with a record of (30-12)");
    }

    @Test
    void testStringMatchWithRecordMatchingWithUnnamedVariables() {
        Team mariners = new Team("Seattle", "Mariners", 30, 12);
        String result = PatternMatching.matchRecordPatternsWithUnnamedVariables(mariners);
        assertThat(result).isEqualTo("Team Mariners from Seattle");
    }

    @Test
    void testStringMatchWithRecordMatchingWithUnnamedVariablesAndWhen() {
        Team mariners = new Team("Minnesota", "Twins", 30, 12);
        String result = PatternMatching.matchRecordPatternsWhen(mariners);
        assertThat(result).isEqualTo("Team Twins from Minnesota, a city that starts with M");
    }
}
