package com.xyzcorp.patternmatching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PatternMatchingTest {
    @Test
    void testEnsureThatMatchWorks() {
        assertThat(PatternMatching.match("Hello")).isEqualTo("Hello to you");
    }

    @Test
    void testCompoundMatch() {
        assertThat(PatternMatching.compoundMatch("Hello")).isEqualTo("non empty string");
    }
}
