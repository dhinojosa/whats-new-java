package com.xyzcorp.streamgatherers;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("preview")
public class SimpleGathererTest {

    @Test
    void testMakeAHandcraftedGatherer() {
        Gatherer<Integer, List<Integer>, List<Integer>> myGatherer = new Gatherer<>() {
            @Override
            public Supplier<List<Integer>> initializer() {
               return ArrayList::new;
            }

            @Override
            public Integrator<List<Integer>, Integer, List<Integer>> integrator() {
                return (state, element, downstream) -> {
                    state.add(element);
                    if (state.size() == 10) {
                        downstream.push(new ArrayList<>(state));
                        state.clear();
                    }
                    return true;
                };
            }

            @Override
            public BinaryOperator<List<Integer>> combiner() {
                return (batchA, batchB) -> {
                    System.out.format("Batch A: %s%n", batchA);
                    System.out.format("Batch B: %s%n", batchB);
                    System.out.println("-------------");
                    batchA.addAll(batchB);
                    return batchA;
                };
            }

            @Override
            public BiConsumer<List<Integer>, Downstream<? super List<Integer>>> finisher() {
                return (integers, downstream) ->
                    downstream.push(new ArrayList<>(integers));
            }
        };

        IntStream.rangeClosed(1, 253).boxed().parallel().gather(myGatherer).forEach(System.out::println);
    }














    @Test
    void testSimpleGatherer() {
        Gatherer<Integer, List<Integer>, List<Integer>> myGatherer =
            new Gatherer<>() {
                @Override
                public Supplier<List<Integer>> initializer() {
                    return ArrayList::new;
                }

                @Override
                public Integrator<List<Integer>, Integer, List<Integer>> integrator() {
                    return (state, element, downstream) -> {
                        state.add(element);
                        if (state.size() == 10) {
                            ArrayList<Integer> downstreamList = new ArrayList<>(state);
                            downstream.push(downstreamList);
                            state.clear();
                        }

                        return true;
                    };
                }
            };
            Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).gather(myGatherer).toList();
    }

    @Test
    void testBundleBy10Gatherer() {
        var gatherer = new Gatherer<Integer, ArrayList<Integer>, List<Integer>>() {
            @Override
            public Supplier<ArrayList<Integer>> initializer() {
                return ArrayList::new;
            }

            @Override
            public Integrator<ArrayList<Integer>, Integer, List<Integer>> integrator() {
                return (state, element, downstream) -> {
                    state.add(element);
                    if (state.size() == 10) {
                        ArrayList<Integer> downstreamList = new ArrayList<>(state);
                        downstream.push(downstreamList);
                        state.removeAll(downstreamList);
                    }
                    return true;
                };
            }
        };
        List<List<Integer>> actual = IntStream
            .rangeClosed(1, 50)
            .boxed()
            .gather(gatherer)
            .collect(Collectors.toList());
        List<List<Integer>> expectedList = List.of(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            List.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
            List.of(21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
            List.of(31, 32, 33, 34, 35, 36, 37, 38, 39, 40),
            List.of(41, 42, 43, 44, 45, 46, 47, 48, 49, 50)
        );
        Assertions.assertThat(actual).isEqualTo(expectedList);
    }

    @Test
    void testBundleBy10GathererWithANumberIndivisibleByTen() {
        var gatherer = new Gatherer<Integer, ArrayList<Integer>, List<Integer>>() {
            @Override
            public Supplier<ArrayList<Integer>> initializer() {
                return ArrayList::new;
            }

            @Override
            public Integrator<ArrayList<Integer>, Integer, List<Integer>> integrator() {
                return (state, element, downstream) -> {
                    state.add(element);
                    if (state.size() == 10) {
                        ArrayList<Integer> downstreamList = new ArrayList<>(state);
                        downstream.push(downstreamList);
                        state.removeAll(downstreamList);
                    }
                    return true;
                };
            }
        };
        List<List<Integer>> actual = IntStream.rangeClosed(1, 53)
            .boxed().gather(gatherer).collect(Collectors.toList());
        List<List<Integer>> expectedList = List.of(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            List.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
            List.of(21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
            List.of(31, 32, 33, 34, 35, 36, 37, 38, 39, 40),
            List.of(41, 42, 43, 44, 45, 46, 47, 48, 49, 50)
        );
        Assertions.assertThat(actual).isEqualTo(expectedList);
    }

    @Test
    void testBundleBy10GathererWithANumberIndivisibleByTenWithRemainders() {
        var gatherer = new Gatherer<Integer, ArrayList<Integer>, List<Integer>>() {
            @Override
            public Supplier<ArrayList<Integer>> initializer() {
                return ArrayList::new;
            }

            @Override
            public Integrator<ArrayList<Integer>, Integer, List<Integer>> integrator() {
                return (state, element, downstream) -> {
                    state.add(element);
                    if (state.size() == 10) {
                        ArrayList<Integer> downstreamList = new ArrayList<>(state);
                        downstream.push(downstreamList);
                        state.removeAll(downstreamList);
                    }
                    return true;
                };
            }

            @Override
            public BiConsumer<ArrayList<Integer>, Downstream<? super List<Integer>>>
            finisher() {
                return new BiConsumer<ArrayList<Integer>, Downstream<? super List<Integer>>>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Downstream<? super List<Integer>> downstream) {
                        downstream.push(integers);
                    }
                };
            }
        };
        List<List<Integer>> actual = IntStream.rangeClosed(1, 53)
            .boxed().gather(gatherer).collect(Collectors.toList());
        List<List<Integer>> expectedList = List.of(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            List.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
            List.of(21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
            List.of(31, 32, 33, 34, 35, 36, 37, 38, 39, 40),
            List.of(41, 42, 43, 44, 45, 46, 47, 48, 49, 50),
            List.of(51, 52, 53)
        );
        Assertions.assertThat(actual).isEqualTo(expectedList);
    }

    @Test
    void testGathererOfStaticMethod() {
        /* Notice this is lambda and separate */
        Gatherer.Integrator<ArrayList<Integer>, Integer, List<Integer>> integrator =
            new Gatherer.Integrator<ArrayList<Integer>, Integer, List<Integer>>() {
                @Override
                public boolean integrate(ArrayList<Integer> state, Integer element, Gatherer.Downstream<? super List<Integer>> downstream) {
                    state.add(element);
                    if (state.size() == 10) {
                        ArrayList<Integer> downstreamList = new ArrayList<>(state);
                        downstream.push(downstreamList);
                        state.removeAll(downstreamList);
                    }
                    return true;
                }
            };

        /* Notice this is lambda and separate */
        Gatherer<Integer, ArrayList<Integer>, List<Integer>> gatherer =
            Gatherer.of(
                ArrayList::new, integrator, (integers, integers2) -> {
                integers.addAll(integers2);
                return integers;
            }, (state, downstream) -> {
                downstream.push(state);
            });

        List<List<Integer>> actual = IntStream.rangeClosed(1, 53).boxed().gather(gatherer).collect(Collectors.toList());
        List<List<Integer>> expectedList = List.of(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            List.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
            List.of(21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
            List.of(31, 32, 33, 34, 35, 36, 37, 38, 39, 40),
            List.of(41, 42, 43, 44, 45, 46, 47, 48, 49, 50),
            List.of(51, 52, 53)
        );
        Assertions.assertThat(actual).isEqualTo(expectedList);
    }

    @Test
    void testStatelessGatherer() {
        Gatherer<Integer, Void, Integer> customFlatMap =
            Gatherer.of((_, element, downstream) -> {
            downstream.push(element);
            downstream.push(element + 1);
            downstream.push(element + 2);
            return true;
        });
        List<Integer> result = Stream.of(1, 2, 3, 4, 5).gather(customFlatMap).toList();
        Assertions.assertThat(result).isEqualTo(List.of(1, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5, 6, 5, 6, 7));
    }

    @Test
    void testBundleByCustomGathererBy10() {
        Gatherer<Integer, List<Integer>, List<Integer>> gatherer = bundleBy(10);
        List<List<Integer>> actual = IntStream.rangeClosed(1, 50)
            .boxed().gather(gatherer).collect(Collectors.toList());
        List<List<Integer>> expectedList = List.of(
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            List.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
            List.of(21, 22, 23, 24, 25, 26, 27, 28, 29, 30),
            List.of(31, 32, 33, 34, 35, 36, 37, 38, 39, 40),
            List.of(41, 42, 43, 44, 45, 46, 47, 48, 49, 50)
        );
        Assertions.assertThat(actual).isEqualTo(expectedList);
    }

    @Test
    void testBundleByCustomGathererBy5() {
        Gatherer<Integer, List<Integer>, List<Integer>> gatherer = bundleBy(5);
        List<List<Integer>> actual = IntStream.rangeClosed(1, 50).boxed().gather(gatherer).collect(Collectors.toList());
        List<List<Integer>> expectedList = List.of(
            List.of(1, 2, 3, 4, 5),
            List.of(6, 7, 8, 9, 10),
            List.of(11, 12, 13, 14, 15),
            List.of(16, 17, 18, 19, 20),
            List.of(21, 22, 23, 24, 25),
            List.of(26, 27, 28, 29, 30),
            List.of(31, 32, 33, 34, 35),
            List.of(36, 37, 38, 39, 40),
            List.of(41, 42, 43, 44, 45),
            List.of(46, 47, 48, 49, 50)
        );
        Assertions.assertThat(actual).isEqualTo(expectedList);
    }

    private Gatherer<Integer, List<Integer>, List<Integer>> bundleBy(int i) {
        return Gatherer.of(ArrayList::new,
            Gatherer.Integrator.of((state, element, downstream) -> {
                state.add(element);
                if (state.size() == i) {
                    ArrayList<Integer> downstreamList = new ArrayList<>(state);
                    downstream.push(downstreamList);
                    state.removeAll(downstreamList);
                }
                return true;
            }), (integers, integers2) -> {
                integers.addAll(integers2);
                return integers;
            }, (integers, downstream) -> {
                if (!integers.isEmpty()) downstream.push(integers);
            });
    }


    @Test
    void testGathererGreedy() {
        Gatherer.Integrator.Greedy<List<Integer>, Integer, Double> listIntegerDoubleGreedyIntegrator =
            Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
                System.out.printf("Integrator Invoked %s on Thread %s%n", LocalDateTime.now(), Thread.currentThread());
                state.add(element);
                if (state.size() == 10) {
                    int sum = state.stream().mapToInt(x -> x).sum();
                    System.out.println("Pushing Downstream");
                    downstream.push(sum + 0.0);
                    return false;
                }
                return true;
            });

        Gatherer<Integer, List<Integer>, Double> gatherer =
            Gatherer.ofSequential(ArrayList::new, listIntegerDoubleGreedyIntegrator);
        IntStream.range(0, 1000).boxed().gather(gatherer).forEach(System.out::println);
    }

    @Test
    void testGathererNonGreedy() {
        //Not use iterator
        //Tried to merge both Gatherer.collect
        //tryAdvance regular
        //forEachRemaining greedy

        Gatherer.Integrator<List<Integer>, Integer, Double> listIntegerDoubleIntegrator =
            Gatherer.Integrator.of((state, element, downstream) -> {
                System.out.printf("Integrator Invoked %s on Thread %s%n", LocalDateTime.now(), Thread.currentThread());
                state.add(element);
                if (state.size() == 10) {
                    int sum = state.stream().mapToInt(x -> x).sum();
                    System.out.println("Pushing Downstream");
                    downstream.push(sum + 0.0);
                    return false;
                }
                return true;
            });

        Gatherer<Integer, List<Integer>, Double> gatherer =
            Gatherer.ofSequential(ArrayList::new, listIntegerDoubleIntegrator);

        IntStream.range(0, 1000).boxed().gather(gatherer).forEach(System.out::println);
    }


    @Test
    void testParallelAndCombine() {
        Gatherer.Integrator.Greedy<List<Integer>, Integer, Integer> integrator = Gatherer.Integrator.ofGreedy(new Gatherer.Integrator.Greedy<List<Integer>, Integer, Integer>() {
            @Override
            public boolean integrate(List<Integer> state, Integer element, Gatherer.Downstream<? super Integer> downstream) {
                state.add(element);
                return true;
            }
        });

        BinaryOperator<List<Integer>> combiner = (integers, integers2) -> {
            System.out.printf("Batch A: %s\nBatch B: %s\n\n", integers, integers2);
            integers.addAll(integers2);
            return integers;
        };

        BiConsumer<List<Integer>, Gatherer.Downstream<? super Integer>> finisher =
            (integers, downstream) -> {
                System.out.printf("Final Batch: %s\n\n", integers);
                integers.forEach(downstream::push);
            };

        Gatherer<Integer, List<Integer>, Integer> gatherer = Gatherer.of(ArrayList::new, integrator, combiner, finisher);

        //Not at all guaranteed to be in ordered due to not abiding by encountered order of parallel
        IntStream.range(0, 100).parallel().boxed().gather(gatherer).forEach(System.out::println);

        System.out.println("----");

        //Will be in order
        IntStream.range(0, 100).parallel().boxed().gather(gatherer).forEachOrdered(System.out::println);

        System.out.println("----");
        IntStream.range(0, 100).parallel().forEach(System.out::println);
    }
}
