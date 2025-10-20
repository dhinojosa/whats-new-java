package com.xyzcorp.streamgatherers;


import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.xyzcorp.streamgatherers.Zippers.*;

public class ZipperGathererTest {

    @Test
    void testInfiniteZipperGatherer() {
        Stream<Integer> streamA = Stream.iterate(0, i -> i + 1);
        Stream<Integer> streamB = Stream.iterate(1, i -> i * 2);
        Stream<String> zipped = streamA.gather(zip(streamB).with((i, j) -> String.format("%s - %s", i, j)));
        System.out.println(zipped.limit(10).toList());
    }

    private static Stream<Integer> fibonacciSequence() {
        record Pair<A, B>(A a, B b){}
        return Stream.concat(Stream.of(0), Stream.iterate(new Pair<>(0, 1), p -> new Pair<>(p.b(), p.a() + p.b())).map(Pair::b));
    }

    @Test
    void testInfiniteZipperGathererAttemptAtFibonacci() {
        Stream<Integer> fibonacci = fibonacciSequence();
        fibonacci.limit(10).forEach(System.out::println);
    }
}
