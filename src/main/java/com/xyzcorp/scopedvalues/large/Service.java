package com.xyzcorp.scopedvalues.large;

import java.util.NoSuchElementException;
import java.util.concurrent.StructuredTaskScope;

public class Service {
    private final Repository repository = new Repository();

    public String run() {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            printThreadAndKey("Begin: In Service::run()");
            var id = scope.fork(repository::persist);
            var employee = scope.fork(repository::find);
            scope.join();
            printThreadAndKey("End: In Service::run()");
            return String.format("Found id of %d and employee %s", id.get(), employee.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printThreadAndKey(String label) {
        try {
            System.out.format("%s: %s contains key \"%s\"\n", label, Thread.currentThread(), Application.KEY.get());
        } catch (NoSuchElementException e) {
            System.out.format("%s: %s has no key!\n", label, Thread.currentThread());
        }
    }
}
