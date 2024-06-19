package com.xyzcorp.conditional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConditionalTest {

    @Test
    void testLessThan10() {
        String result = Conditional.tenStatus(3);
        Assertions.assertThat(result).isEqualTo("Less than 10");
    }
}
