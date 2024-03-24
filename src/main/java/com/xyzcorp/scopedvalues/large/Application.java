package com.xyzcorp.scopedvalues.large;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.concurrent.StructuredTaskScope;

public class Application {
    protected static ScopedValue<String> KEY = ScopedValue.newInstance();

    private static final Service service = new Service();

    public static void main(String[] args) {
        ScopedValue.where(KEY, "Hello").run(task());
        ScopedValue.where(KEY, "Bon Jour").run(task());
    }

    @NotNull
    private static Runnable task() {
        return () -> {
            printThreadAndKey("In task, before structured scope");
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                printThreadAndKey("In task, in structured scope");
                var string = scope.fork(service::run);
                scope.join();
                System.out.println(STR."Result is \{string.get()}");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printThreadAndKey("In task, after structured scope");
        };
    }

    private static void printThreadAndKey(String label) {
        try {
            System.out.format("%s: %s contains key \"%s\"\n", label, Thread.currentThread(), Application.KEY.get());
        } catch (NoSuchElementException e) {
            System.out.format("%s: %s has no key!\n", label, Thread.currentThread());
        }
    }
}
