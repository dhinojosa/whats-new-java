package com.xyzcorp.streamgatherers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;
import java.util.stream.IntStream;

public class GroupByGatherer {

    static class GroupBy<T, K> implements Gatherer<T, HashMap<K, List<T>>, Map.Entry<K, List<T>>> {

        private final Function<T, K> groupFunction;

        public GroupBy(Function<T, K> groupFunction) {
            this.groupFunction = groupFunction;
        }

        @Override
        public Supplier<HashMap<K, List<T>>> initializer() {
            return HashMap::new;
        }

        @Override
        public Integrator<HashMap<K, List<T>>, T, Map.Entry<K, List<T>>> integrator() {
            return (state, element, _) -> {
                K result = groupFunction.apply(element);
                state.computeIfAbsent(result, _ -> new ArrayList<>());
                state.get(result).add(element);
                return true;
            };
        }

        public static <T, K> Gatherer<T, HashMap<K, List<T>>, Map.Entry<K, List<T>>> groupBy(Function<T, K> groupFunction) {
            return new GroupBy<>(groupFunction);
        }

        @Override
        public BiConsumer<HashMap<K, List<T>>, Downstream<? super Map.Entry<K, List<T>>>> finisher() {
            return (kListHashMap, downstream) -> kListHashMap.entrySet().forEach(downstream::push);
        }
    }

    public static void main(String[] args) {
        System.out.println(IntStream.range(0, 100).boxed()
            .gather(GroupBy.groupBy(i -> i % 2 == 0 ? "even" : "odd"))
            .collect(Collectors.toList()));

        //IntStream.range(0, 100).boxed().collect(Collectors.groupingBy(i -> i % 2 == 0? "even":"odd").entrySet.stream
    }
}