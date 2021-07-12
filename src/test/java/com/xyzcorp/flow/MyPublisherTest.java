package com.xyzcorp.flow;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class MyPublisherTest {

    @Test
    void testPublishAndSubscriber() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        MyPublisher myPublisher = new MyPublisher(executorService);

        CountDownLatch countDownLatch = new CountDownLatch(2);

        myPublisher.subscribe(new Flow.Subscriber<Long>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(100);
            }

            @Override
            public void onNext(Long item) {
                System.out.println("S1:" + item + ": " + Thread.currentThread() );
                if (item == 90L) this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("S1: Done");
                countDownLatch.countDown();
            }
        });

        myPublisher.subscribe(new Flow.Subscriber<Long>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(10000);
            }

            @Override
            public void onNext(Long item) {
                System.out.println("S2:" + item + ": " + Thread.currentThread());
                if (item == 500L) this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("S2: Done");
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        executorService.shutdown();
        System.out.println("Hooray we are awesome!");

    }
}
