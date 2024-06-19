package com.xyzcorp.loom;

public class EvenMoreBasicVirtualThread {

    public static void main(String[] args) throws InterruptedException {
        Thread newThread =
            Thread.ofVirtual().name("my-virtual-thread").unstarted(() -> {
                System.out.println(Thread.currentThread());
                System.out.println("Operating in new thread");
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        newThread.start();
        newThread.join(); //block until the thread is done
    }
}
