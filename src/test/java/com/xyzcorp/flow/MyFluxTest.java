package com.xyzcorp.flow;

import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;

public class MyFluxTest {

    @Test
    void testFluxWithEmitter() {
        Flux<Integer> integerFlux = Flux.create(fluxSink -> {
            fluxSink.next(10);
            fluxSink.next(12);
            fluxSink.complete();
        }, FluxSink.OverflowStrategy.LATEST);


        integerFlux.subscribe(System.out::println,
            Throwable::printStackTrace,
            () -> System.out.println("Complete"));
    }

    @Test
    void testFlux() {
        Flux<Integer> just = Flux.just(1, 3, 4, 5);
        just
            .map(x -> x * 4)
            .subscribe(integer -> System.out.println("Received:" + integer));

        just.filter(x -> x % 2 != 0)
            .subscribe(integer -> System.out.println("Received Odd:" + integer));
    }


    @Test
    public void testSchedulers() throws InterruptedException {
        Flux.just(1, 3, 4, 5, 10, 100)     // io
            .doOnNext(i -> debug("L1", i))  // io
            .publishOn(Schedulers.parallel())
            .doOnNext(i -> debug("L2", i))
            .subscribeOn(Schedulers.boundedElastic())
            .map(i -> i * 10)
            .publishOn(Schedulers.boundedElastic())// io
            .doOnNext(i -> debug("L3", i))  // io
            .subscribe(i -> debug("L4", i));
        Thread.sleep(10000);
    }

    public <A> void debug(String label, A a) {
        System.out.printf("%s: %s - %s\n", label, a,
            Thread.currentThread().getName());
    }
}
