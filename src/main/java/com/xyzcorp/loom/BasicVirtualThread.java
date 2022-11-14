package com.xyzcorp.loom;

import java.util.concurrent.*;

public class BasicVirtualThread {
    public static void main(String[] args)
        throws ExecutionException, InterruptedException {
        ThreadFactory tf =
            Thread.ofVirtual().name("virtual-with-executors").factory();
        try (ExecutorService executorService =
                 Executors.newThreadPerTaskExecutor(tf)) {
            Future<Integer> submit = executorService.submit(() -> {
                System.out.format("Running in Thread: %s\n",
                    Thread.currentThread());
                System.out.format("Is our thread virtual? %b\n",
                    Thread.currentThread().isVirtual());
                return 100;
            });


            //This is now a join where it was a block. Virtual Threads are
            // different
            System.out.println(submit.get());//We shall no fear of blocking
        }
    }
}
