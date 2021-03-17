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
        subscriber.onSubscribe(new Flow.Subscription() {
            final AtomicBoolean done = new AtomicBoolean(false);
            final AtomicBoolean started = new AtomicBoolean(false);
            final AtomicLong counter = new AtomicLong(0L);
            final AtomicLong requests = new AtomicLong(0L);

            @Override
            public void request(long n) {
                requests.addAndGet(n);
                if (!started.get()) {
                    startLoop();
                    started.set(true);
                }
            }

            private void startLoop() {
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
