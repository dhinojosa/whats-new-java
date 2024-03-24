package com.xyzcorp.scopedvalues.nested;

import java.util.NoSuchElementException;

public class NestedScopedValues {
    static ScopedValue<String> KEY = ScopedValue.newInstance();

    /**
     * The main method in the ChainedScopedValues class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runNestedScopedValues();
    }

    /**
     * Executes a series of nested scopes using the ScopedValue class.
     * Each nested scope sets a value for a specific key and runs a given function.
     * The function can access the value of the key within its scope.
     * <p>
     * Notice that the thread didn't change. The point was not to create a Thread, but to
     * Provide a KEY and a value depending on the scope.
     */
    private static void runNestedScopedValues() {
        ScopedValue.where(KEY, "Do").run(() ->
            ScopedValue.where(KEY, "Ra").run(() ->
                ScopedValue.where(KEY, "Me").run(() ->
                    ScopedValue.where(KEY, "Fa").run(() ->
                        ScopedValue.where(KEY, "So").run(() ->
                            ScopedValue.where(KEY, "La").run(() ->
                                ScopedValue.where(KEY, "Te").run(() ->
                                        printThreadAndKey("Inside the nest"))))))));
        printThreadAndKey("Outside of the chain");
    }

    private static void printThreadAndKey(String label) {
        try {
            System.out.format("%s: %s contains key \"%s\"\n", label, Thread.currentThread(), KEY.get());
        } catch (NoSuchElementException e) {
            System.out.format("%s: %s has no key!\n", label, Thread.currentThread());
        }
    }
}
