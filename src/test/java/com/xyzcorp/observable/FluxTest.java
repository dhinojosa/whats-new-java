package com.xyzcorp.observable;

import com.xyzcorp.flow.MyPublisherReactive;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FluxTest {
    @Test
    void testFluxFromPublisher() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Flux.from(new MyPublisherReactive(executorService))
            .take(14)
            .map(x -> x * 3)
            .subscribe(System.out::println, Throwable::printStackTrace,
                () -> System.out.println("Done"));
        Thread.sleep(1000);
    }
}
