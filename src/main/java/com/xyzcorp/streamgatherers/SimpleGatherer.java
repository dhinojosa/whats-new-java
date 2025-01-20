package com.xyzcorp.streamgatherers;

import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class SimpleGatherer {

    public static class MyGatherer implements Gatherer<Integer, ArrayList<Integer>, List<Integer>> {

        @Override
        public Supplier<ArrayList<Integer>> initializer() {
            return ArrayList::new;
        }

        @Override
        public Integrator<ArrayList<Integer>, Integer, List<Integer>> integrator() {
            return new Integrator<ArrayList<Integer>,Integer,List<Integer>>() {
                @Override
                public boolean integrate(ArrayList<Integer> state, Integer element,
                        Downstream<? super List<Integer>> downstream) {
                    state.add(element);
                    if (isPrime(state.stream().mapToInt(x -> x).sum()) || state.size() == 10) {
                        ArrayList<Integer> downstreamList = new ArrayList<>(state);
                        downstream.push(downstreamList);
                        state.removeAll(downstreamList);
                    }
                    return true;
                }

                private boolean isPrime(int i) {
                    if (i <= 1) return false;
                    if (i == 2) return true;
                    if (i % 2 == 0) return false;
                    for (int j = 3; j * j <= i; j += 2) {
                        if (i % j == 0) return false;
                    }
                    return true;
                }
            };

        }
    }
    public static void main(String[] args) {
       var result = IntStream.rangeClosed(1, 100).boxed().gather(new MyGatherer()).toList();
       System.out.println(result);
    }

}
