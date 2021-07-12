package com.xyzcorp.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class MyPublisher implements Flow.Publisher<Long> {
    private final ExecutorService executorService;

    public MyPublisher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Long> subscriber) {
        AtomicBoolean done = new AtomicBoolean();
        AtomicBoolean started = new AtomicBoolean();
        AtomicLong counter = new AtomicLong();
        AtomicLong requests = new AtomicLong();

        subscriber.onSubscribe(new Flow.Subscription() {
            @Override
            public void request(long n) {
                requests.addAndGet(n);
                if (!started.get()) {
                    startLoop();
                    started.set(true);
                }
            }


            public void startLoop() {
                executorService.submit(() -> {
                    while (!done.get()) {
                        if (requests.decrementAndGet() >= 0) {
                            subscriber.onNext(counter.incrementAndGet());
                        }
                    }
                    subscriber.onComplete();
                    started.set(false);
                });
            }

            @Override
            public void cancel() {
                done.set(true);
            }
        });
    }
}
