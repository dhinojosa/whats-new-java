package com.xyzcorp.flow;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class MyFluxTest {

    @Test
    void testFlux() {
        Flux<Integer> just = Flux.just(1, 3, 4, 5);
        just.subscribeOn(Schedulers.parallel());
        just.publishOn(Schedulers.boundedElastic());


//        Flux.from(new Publisher<Long>() {
//            @Override
//            public void subscribe(Subscriber<? super Long> s) {
//
//            }
//        })
    }
}
