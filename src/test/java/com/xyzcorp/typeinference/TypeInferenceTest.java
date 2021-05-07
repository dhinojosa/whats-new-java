package com.xyzcorp.typeinference;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TypeInferenceTest {

    @Test
    void testTypeInference() {
        var list = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<>();
    }

    @Test
    void testTypeInferenceUsingStaticMethod() {
        var list = Collections.emptyList();
        List<String> list2 = Collections.emptyList();
    }

    @Test
    void testUsingAPath() throws IOException {
        var path = Paths.get("myfile.log");
        try (var lines = Files.lines(path)) {

        }
    }

    @Test
    void testNonDenotableType() {
        var a = new Object() {
            String firstName;
            String lastName;
            String age;
        };
    }


    @Test
    void testUsingNonDenotableTypes() {
        Stream.of(1,2,3,4,5).map(i -> new Object() {
                int prev = i - 1;
                int curr = i;
                int next = i + 1;
                int negative = i - 1;
            }).forEach(o -> {
                System.out.printf("prev: %d, curr: %d, next: %d, negative: %d\n",
                    o.prev, o.curr, o.next, o.negative);
        });
    }
}
