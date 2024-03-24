package com.xyzcorp.scopedvalues.structuredconcurrency;

import java.util.NoSuchElementException;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyScopedValues {
    static ScopedValue<String> KEY = ScopedValue.newInstance();

    /**
     * The main method in the ChainedScopedValues class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runScopeValueFirst();
        //runScopeValueLast();
    }

    /**
     * Demonstrates the usage of nested scoped values and virtual threads.
     * <p>
     * The method sets a scoped value with the key "Hello" and value "KEY".
     * It then creates a virtual thread named "in-scope"
     * inside this scoped value.
     * Within the virtual thread, a method named `printThreadAndKey` is called to print the
     * current thread and the value associated with the scoped value key.
     * After exiting the virtual thread, the method
     * `printThreadAndKey` is called again to print the thread and key value,
     * this time outside the scope of the nested scoped value.
     */
    private static void runScopeValueFirst() {
        ScopedValue.where(KEY, "Hello").run(() -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                scope.fork(() -> {
                    printThreadAndKey("Inside fork");
                    return null;
                });
                scope.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printThreadAndKey("Inside of the scope");
        });

        printThreadAndKey("Outside of the scope");
    }


    /**
     * The following will not work and will throw a StructureViolationException
     * This will occur when we `fork` the call. `ScopedValue` should come first,
     * and then the `StructuredTaskScope`
     */
    private static void runScopeValueLast() {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            ScopedValue.where(KEY, "Hello").run(() -> {
                try {
                    scope.fork(() -> {
                        printThreadAndKey("Inside fork");
                        return null;
                    });
                    scope.join();
                    printThreadAndKey("Inside of the scope");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

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
