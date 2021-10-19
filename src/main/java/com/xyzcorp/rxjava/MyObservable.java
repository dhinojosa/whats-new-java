package com.xyzcorp.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MyObservable {

    public static void sendToKafka(int i) {
        //lots of code
    }
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        observable.map(x -> x * 2).subscribe(System.out::println, Throwable::printStackTrace);
        observable
            .observeOn(Schedulers.io())
            .filter(x -> x % 2 == 0).subscribe(MyObservable::sendToKafka);
    }
}
