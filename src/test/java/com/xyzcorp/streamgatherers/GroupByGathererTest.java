package com.xyzcorp.streamgatherers;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("preview")
public class GroupByGathererTest {
    @Test
    void testGroupByGatherer() {
        Map<String, List<Integer>> results = IntStream.range(1, 10).boxed()
            .gather(GroupByGatherer.GroupBy.groupBy(i -> i % 2 == 0 ? "even" : "odd"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Assertions.assertThat(results.get("even")).containsExactly(2, 4, 6, 8);
        Assertions.assertThat(results.get("odd")).containsExactly(1, 3, 5, 7, 9);
    }
}
