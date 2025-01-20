package com.xyzcorp.beforesuper;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositiveBigIntegerTest {

    @Test
    void testAttemptToCreatePositiveBigInteger() {
        PositiveBigInteger bigInteger = new PositiveBigInteger(19);
        assertThat(bigInteger).isEqualTo(19);
    }

    @Test
    void testAttemptToCreateNegativeBigInteger() {
        assertThatThrownBy(() -> new PositiveBigInteger(-19));
    }
}
