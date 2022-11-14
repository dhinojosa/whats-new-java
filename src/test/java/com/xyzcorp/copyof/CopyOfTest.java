package com.xyzcorp.copyof;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CopyOfTest {
    /**
     * Returns an unmodifiable List containing the elements of the given
     * Collection, in its iteration order. The given Collection must not be
     * null,
     * and it must not contain any null elements. If the given Collection is
     * subsequently modified, the returned List will not reflect such
     * modifications.
     */
    @Test
    void testCopyOf() {
        List<Integer> mutableList = Arrays.asList(1, 2, 3);
        List<Integer> copyList = List.copyOf(mutableList);
        assertThatThrownBy(() -> copyList.add(4))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    /**
     * If a value is present, returns the value, otherwise throws an
     * exception produced by the exception supplying function. Available
     * for java.util.Optional, java.util.OptionalDouble,
     * java.util.OptionalInt and java.util.OptionalLong
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    void testOrElseThrow() {
        assertThatThrownBy(() -> Stream.of(1,2,3,4)
              .filter(i -> i > 10)
              .findFirst()
              .orElseThrow(() -> new RuntimeException("No number higher than 10")))
            .isInstanceOf(RuntimeException.class);
    }
}
