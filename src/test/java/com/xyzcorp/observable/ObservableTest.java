package com.xyzcorp.observable;

import com.xyzcorp.flow.MyPublisher;
import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableTest {


    @Test
    void testObservableFromPublisher() {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        //Observable.fromPublisher(new MyPublisher(executorService));
    }
}
