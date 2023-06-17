package com.xyzcorp.typeinference;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeInferenceTest {
    @SuppressWarnings("RedundantTypeArguments")
    @Test
    void testBeforeTypeInference() {
        List<String> cs = Collections.<String>emptyList();
        assertThat(cs).isEmpty();
    }

    @Test
    void testAfterTypeInference() {
        var cs = Collections.<String>emptyList();
        assertThat(cs).isEmpty();
    }

    @Test
    void testCompleteExampleWithVar() {
        var path = Paths.get("web.log");
        try (var lines = Files.lines(path)) {
            var warningCount
                = lines
                .filter(line -> line.contains("WARNING"))
                .count();
            System.out.printf("Found %d warnings in the log file",
                warningCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWhyWeMayNeedTypeInference() {
        var lists = List.of(List.of(List.of(1, 2, 3),
            List.of(4, 5,
                6), List.of(30, 10, 50, 102)));
        System.out.println(lists);
    }

    @SuppressWarnings("unused")
    @Test
    void testPolymorphismWithVar() {
        var oregonian = new Oregonian();
        //oregonian = new NorthAmerican(); Type is locked in
    }

    // No way to determine the types
    // public void add2(var a, var b) {
    //   a + b
    // }
    public String times(int n, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
    // You cannot assign to null either
    // var x = null;

    // This will not work either
    //  var x = () -> {}



    @Test
    void testLifeWithoutNonDenotableTypes() {

        record MyHolder(int value) {
            public int next() {
                return value + 1;
            }

            public int previous() {
                return value - 1;
            }

            public int negative() {
                return -value;
            }
        }

        var myHolderStream =
            Stream.of(1, 2, 3, 4, 5)
                  .map(MyHolder::new)
                  .map(MyHolder::negative)
                  .toList();
    }

    /**
     * In the following, what is the type?
     * It can’t be an Object, because we can call name and age on it,
     * Instead, the true type is non-denotable — it
     * doesn't have a name we can use as the type of variable in Java code.
     * At runtime the type is just a compiler-assigned placeholder.
     */
    @Test
    void nonDenotableTypes() {
        var a = new Object() {
            final String name = "Larry Java";
            final int age = 40;
        };

        System.out.println(a.name);
        System.out.println(a.age);
    }

    @SuppressWarnings("SimplifyStreamApiCallChains")
    @Test
    void usingANonDenotableTypeToPassInAction() {
        var result =
            List.of(4, 3, 10, 11).stream().map(x -> new Object() {
                final int before = x - 1;
                final int after = x + 1;
                final int negative = -x;
            });

        result.forEach(o ->
            System.out.printf("Before: %d, After: %d, Negative: %d\n",
                o.before, o.after, o.negative));
    }
}
