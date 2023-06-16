package com.xyzcorp.objects;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ObjectsTest {

    @Test
    void testObjectsNonNull() {
        String s = null;
        Objects.requireNonNull(s, "This string should not be null");
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
