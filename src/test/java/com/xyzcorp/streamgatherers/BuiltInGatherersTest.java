package com.xyzcorp.streamgatherers;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuiltInGatherersTest {

    /**
     * This is an many-to-one gatherer that constructs an aggregate incrementally until no more input elements exist.
     * It has two parameters:
     * <p>
     * initial: This is the identity value or the value that the gatherer emits if the
     * input stream contains no elements.
     * folder: This is a lambda expression that contains two parameters: the first is
     * the aggregate the gatherer is constructing and the second is the element that's currently being processed.
     */
    @Test
    void testFold() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).gather(Gatherers.fold(() -> "",
                (s, integer) -> s.concat(String.valueOf(integer))))
            .map(s -> "$" + s + "$")
            .forEach(System.out::println);
    }

    /**
     * This is a one-to-one gatherer that performs a prefix scan, which is an incremental accumulation.
     * Starting with an initial value obtained from the parameter initial,
     * it obtains subsequent values by applying scanner to the current value and the next input element.
     * The gatherer then emits the value downstream.
     * <p>
     * Great for running balances, etls,
     */
    @Test
    void testScan() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).gather(Gatherers.scan(() -> 0, (aggregate, next) -> {
            System.out.printf("Current: %d\tNext: %d%n", aggregate, next);
            return aggregate + next;
        })).forEach(System.out::println);
    }

    /**
     * This is a many-to-many gatherer that gathers elements in windows, which are encounter-ordered
     * groups of elements. The parameter windowSize specifies the size of the windows.
     * <p>
     * Great for bundling items
     */
    @Test
    void testWindowFixed() {
        List<List<Integer>> windows =
            Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.windowFixed(3)).toList();

        windows.forEach(System.out::println);
    }


    /**
     * Similar to windowFixed, this is a many-to-many gatherer that gathers elements in windows.
     * However, each subsequent window includes all elements of the previous window except for its first element,
     * and adds the next element in the stream.
     */
    @Test
    void testWindowSliding() {
        List<List<Integer>> moreWindows =
            Stream.of(1, 2, 3, 4, 5, 6, 7, 8).gather(Gatherers.windowSliding(3)).toList();
        moreWindows.forEach(System.out::println);
    }


    /**
     * This is a one-to-one gatherer that invokes mapper for each input element in the stream concurrently,
     * up to the limit specified by maxConcurrency.
     * You can use this limit for the following:
     * <p>
     * As a rate-limiting construct to prevent the gatherer from issuing too many concurrent requests to
     * things like an external service or a database
     * As a performance-enhancer to enable multiple, separate operations to be performed concurrently
     * while avoiding converting the entire stream into a parallel stream
     */
    @Test
    void testConcurrentMap() {
        Consumer<Integer> debugConsumer = i -> {
            System.out.printf("Consumer Value: %d\tThread:%s%n",
                i, Thread.currentThread());
        };

        Function<Integer, Integer> debugFunction = i -> {
            try {
                Thread.sleep(4000);
                System.out.printf("Function Value: %d\tThread:%s%n", i, Thread.currentThread());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return i;
        };

        //Add parallel to see results
        IntStream.range(0, 20).boxed().parallel()
            .gather(Gatherers.mapConcurrent(5, debugFunction)).forEach(debugConsumer);
    }

    /**
     * The following example composes a new gatherer with the scan and fold gatherers examples as described
     */
    @Test
    void combiningGatherers() {
        Gatherer<Integer, ?, Integer> sc =
            Gatherers.scan(() -> 100,
                (current, next) -> current + next);

        Gatherer<Integer, ?, String> fo =
            Gatherers.fold(() -> "",
                (result, element) -> {
                    if (result.equals("")) return element.toString();
                    return result + ";" + element;
                });

        var t = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            .gather(sc.andThen(fo))
            .findFirst()
            .get();

        System.out.println(t);
    }
}
