package com.xyzcorp.gatherers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class GathererTest {

    @Test
    void testGatherer() {
        Stream<Integer> integerStream = Stream.of(10, 20, 50, 5, 1, 3, 4, 20, 100, 1, 2, 4, 120, 190, 210);
        List<List<Integer>> collect = integerStream.gather(new Gatherer<Integer, List<Integer>, List<Integer>>() {
            @Override
            public Supplier<List<Integer>> initializer() {
                return ArrayList::new; //state
            }

            @Override
            public Integrator<List<Integer>, Integer, List<Integer>> integrator() {
                return (state, element, downstream) -> {
                    //updating the state?
                    state.add(element);
                    if (state.stream().mapToInt(i -> i).sum() >= 100) {
                        downstream.push(List.copyOf(state));
                        state.clear();
                    }
                    return true;
                };
            }
        }).toList();

        System.out.println(collect);
    }
}
