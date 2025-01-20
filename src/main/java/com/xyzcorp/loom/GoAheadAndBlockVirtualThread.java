package com.xyzcorp.loom;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class GoAheadAndBlockVirtualThread {
    public static void main(String[] args)
        throws InterruptedException {

        System.out.printf("The Process ID is %d",
            ProcessHandle.current().pid());

        Thread.sleep(20000);

        long startTime = System.currentTimeMillis();
        ThreadFactory tf = Thread
            .ofVirtual()
            .name("thread-go-and-block")
            .factory();
        var numProcessors= Runtime.getRuntime().availableProcessors();
        System.out.println(numProcessors);
        try (
            ExecutorService executorService = Executors.newThreadPerTaskExecutor(tf)) {
//            ExecutorService executorService = Executors.newFixedThreadPool(numProcessors)) {
            Stream<Callable<Integer>> callableStream =
                Stream.iterate(0, integer -> integer + 1).map(i -> () -> {
                    System.out.format("Process(%d) Started: inside of Thread " +
                            "%s\n",
                        i, Thread.currentThread());
                    Thread.sleep(5000); //Block
                    System.out.format("Process(%d) Finished: Inside of Thread" +
                            " %s\n"
                        , i, Thread.currentThread());
                    return 100;
                });

            List<Callable<Integer>> callables =
                callableStream.limit(80).toList();
            executorService.invokeAll(callables);
            executorService.shutdown();
            System.out.println("Done:" + (System.currentTimeMillis() - startTime));
            Thread.sleep(10000);
        }
    }
}
