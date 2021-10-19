package com.xyzcorp.flow;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class MyPublisherTest {

    //reactivestreams.org

    @Test
    void testMyPublisher() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyPublisher myPublisher = new MyPublisher(executorService);
        myPublisher.subscribe(new Flow.Subscriber<Long>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(100000);
            }

            @Override
            public void onNext(Long item) {
                System.out.println(item);
                if(item == 40L) subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });

        myPublisher.subscribe(new Flow.Subscriber<Long>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1000);
            }

            @Override
            public void onNext(Long item) {
                System.out.println("S2:" + item);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });

    }
}
