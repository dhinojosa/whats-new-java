package com.xyzcorp.flow;

import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.Test;

public class MyObservableTest {
    @Test
    void testObservable() {
        Observable.just(1,2,3,4).subscribe(System.out::println);
    }
}
