package com.xyzcorp.patternmatching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PatternMatchingTest {
    @Test
    void testEnsureThatMatchWorks() {
        assertThat(PatternMatching.match("Hello")).isEqualTo("Hello to you");
    }

    @Test
    void testCompoundMatch() {
        assertThat(PatternMatching.compoundMatch("Hello")).isEqualTo("non empty string");
    }

    @Test
    void testStringMatch() {
        PatternMatching.matchString("Foo");
        PatternMatching.matchString("Bar");
        PatternMatching.matchString(null);
    }
}
