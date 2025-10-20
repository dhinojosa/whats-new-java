package com.xyzcorp.gatherers;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class UsingGatherers {
    public static void main(String[] args) {
        Map<String, Integer> result = Stream.of(1, 2, 3, 4, 5, 6)
            .collect(Collectors.groupingBy(integer -> (integer % 2 == 0 ? "even" : "odd")))
            .entrySet()
            .stream()
            .map(e -> Map.entry(e.getKey(), e.getValue().size()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(result);
    }
}
