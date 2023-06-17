package com.xyzcorp.flow;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

public class MyObservableTest {
    @Test
    void testObservable() {
        Observable
            .just(1, 2, 3, 4)
            .subscribe(System.out::println);
    }

    @Test
    void testObservableForking() {
        Observable<Integer> observable = Observable
            .just(1, 2, 3, 4);
        observable.subscribe(System.out::println);
        Observable<Integer> mapped = observable.map(x -> x * 4);

        mapped.subscribe(x -> System.out.println(x));
        mapped.map(x -> x + "!").subscribe(x -> System.out.println(x));
    }


    @Test
    public void testSchedulers() throws InterruptedException {
        Observable.just(1, 3, 4, 5, 10, 100)     // io
                  .doOnNext(i -> debug("L1", i))  // io
                  .observeOn(Schedulers.computation())
                  .doOnNext(i -> debug("L2", i))
                  .subscribeOn(Schedulers.io())// computation
                  .map(i -> i * 10)
                  .observeOn(Schedulers.io())// computation
                  .doOnNext(i -> debug("L3", i))  // io
                  .subscribe(System.out::println);
        Thread.sleep(10000);
    }

    public <A> void debug(String label, A a) {
        System.out.printf("%s: %s - %s\n", label, a,
            Thread.currentThread().getName());
    }
}
