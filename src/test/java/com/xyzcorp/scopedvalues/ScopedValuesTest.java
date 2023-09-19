package com.xyzcorp.scopedvalues;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

public class ScopedValuesTest {

    @Test
    public void testFramework() {
        Framework framework = new Framework();
        framework.performCalculation();
    }

    @Test
    public void testFrameworkWithGet() {
        Framework framework = new Framework();
        String s = framework.performAnotherCalculation();
        assertThat(s).isEqualTo("2023 09 19");
    }

    @Test
    public void testLetsDoAnother() {
        Framework framework = new Framework();
        String s = framework.letsDoAnother();
        assertThat(s).isEqualTo("2023 09 19");
    }

    @Test
    public void testAsynchronously() throws ExecutionException, InterruptedException {
        Framework framework = new Framework();
        Future<String> stringFuture = framework.performingInAnotherThread();
        String result = stringFuture.get();
        assertThat(result).isEqualTo("2023 09 19");
    }

    @Test
    public void testWhereWithRunnable() throws ExecutionException, InterruptedException {
        Framework framework = new Framework();
        framework.performingWithRunWhere();
    }

    @Test
    public void testWhereWithCallable() throws Exception {
        Framework framework = new Framework();
        String result = framework.performingWithCallWhere();
        assertThat(result).isEqualTo("Rate[rate=10]");
    }
}
