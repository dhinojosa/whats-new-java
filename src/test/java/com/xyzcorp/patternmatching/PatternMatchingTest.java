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

    // Preview
    // @Test
    // void testStringMatch() {
    //     PatternMatching.matchString("Foo");
    //     PatternMatching.matchString("Bar");
    //     PatternMatching.matchString(null);
    // }
}
