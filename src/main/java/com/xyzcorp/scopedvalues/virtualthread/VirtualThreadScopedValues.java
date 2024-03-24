package com.xyzcorp.scopedvalues.virtualthread;

import java.time.Duration;
import java.util.NoSuchElementException;

public class VirtualThreadScopedValues {
    static ScopedValue<String> KEY = ScopedValue.newInstance();

    /**
     * The main method in the ChainedScopedValues class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runScopeValue();
    }

    /**
     * The following will not work. You should only use
     * Structured Concurrency or leave it in the same thread.
     */
    private static void runScopeValue() {
        ScopedValue.where(KEY, "Hello").run(() -> {
            try {
                Thread start = Thread
                    .ofVirtual()
                    .name("in-scope")
                    .start(() -> printThreadAndKey("Inside of virtual-thread"));
                start.join(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        printThreadAndKey("Outside of the scope");
    }

    private static void printThreadAndKey(String label) {
        try {
            System.out.format("%s: %s contains key \"%s\"\n", label, Thread.currentThread(), KEY.get());
        } catch (NoSuchElementException e) {
            System.out.format("%s: %s has no key!\n", label, Thread.currentThread());
        }
    }
}
