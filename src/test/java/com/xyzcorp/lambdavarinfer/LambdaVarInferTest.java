package com.xyzcorp.lambdavarinfer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaVarInferTest {

    /**
     * Here we want to place an annotation on a var within a lambda
     * ,so we can use `var` to capture the variable and apply the annotation
     */
    @Test
    void testLambdaVarInfer() {
        var collect = Stream.of(1, 2, 3, 4)
                            .map((@NotNull var integer) -> integer + "!")
                            .collect(Collectors.joining("\n"));
        assertThat(collect).isEqualTo("""
            1!
            2!
            3!
            4!""");
    }
}
