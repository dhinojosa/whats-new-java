package com.xyzcorp.flow;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class MyPublisherReactive implements Publisher<Long> {
    private final ExecutorService executorService;

    public MyPublisherReactive(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void subscribe(Subscriber<? super Long> subscriber) {
        subscriber.onSubscribe(new Subscription() {
            final AtomicBoolean done = new AtomicBoolean(false);
            final AtomicLong counter = new AtomicLong(0);
            final AtomicLong requests = new AtomicLong(0);
            final AtomicBoolean started = new AtomicBoolean(false);

            public void startLoop() {
                executorService.submit(() -> {
                    while (!done.get()) {
                        if (requests.decrementAndGet() >= 0) {
                            subscriber.onNext(counter.incrementAndGet());
                        }
                    }
                });
            }

            @Override
            public void request(long n) {
                requests.addAndGet(n);
                if (started.compareAndSet(false, true)) {
                    startLoop();
                }
            }

            @Override
            public void cancel() {
                done.set(true);
            }
        });
    }
}
