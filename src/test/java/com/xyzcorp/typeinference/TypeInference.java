package com.xyzcorp.typeinference;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TypeInference {
    @Test
    void testBeforeTypeInference() {
        List<String> cs = Collections.<String>emptyList();
    }

    @Test
    void testAfterTypeInference() {
        var cs = Collections.<String>emptyList();
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
    void testPolymorphismWithVar() {
        var oregonian = new Oregonian();
        //oregonian = new NorthAmerican(); Type is locked in
    }

    // No way to determine the types
    // public void add2(var a, var b) {
    //   a + b
    // }

    // You cannot assign to null either
    // var x = null;

    // This will not work either
    //  var x = () -> {}


    /**
     * In the following, what is the type?
     * It can’t be an Object, because we can call name and age on it,
     * Instead, the true type is non-denotable — it
     * doesn’t have a name we can use as the type of a variable in Java code.
     * At runtime the type is just a compiler-assigned placeholder.
     */
    @Test
    void nonDenotableTypes() {
        var a = new Object() {
            String name = "Larry Java";
            int age = 40;
        };

        System.out.println(a.name);
        System.out.println(a.age);
    }

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
