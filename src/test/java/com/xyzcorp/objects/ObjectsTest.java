package com.xyzcorp.objects;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ObjectsTest {

    @SuppressWarnings("DataFlowIssue")
    @Test
    void testObjectsNonNull() {
        String s = null;
        assertThatThrownBy(() -> requireNonNull(s, "This string should not be null"))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testObjectsCheckIndex() {
        assertThatThrownBy(() -> Objects.checkIndex(122, 10)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testObjectsCheckFromToIndex() {
        assertThatThrownBy(() -> Objects.checkFromToIndex(7, 44, 20)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testObjectsCheckFromIndexSize() {
        assertThatThrownBy(() -> Objects.checkFromIndexSize(16, 8, 20)).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
