package com.xyzcorp.beforesuper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SomeTest {
    @Test
    void testRawType() {
        List list = new ArrayList();
    }


    void testFunction() {
        var myFunction = new Function<String, Integer>(){
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        Assertions.assertThat(myFunction.apply("Hello")).isEqualTo(5);
    }
}
