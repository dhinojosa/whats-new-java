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
    @SuppressWarnings("DataFlowIssue")
    @Test
    void testCopyOf() {
        List<Integer> mutableList = Arrays.asList(1, 2, 3);
        List<Integer> copyList = List.copyOf(mutableList);
        assertThatThrownBy(() -> copyList.add(4))
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
