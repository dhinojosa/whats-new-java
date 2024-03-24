package com.xyzcorp.scopedvalues.api;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.StructuredTaskScope;

public class ScopedValuesAPI {
    static ScopedValue<String> GREETING_KEY = ScopedValue.newInstance();
    static ScopedValue<String> FAREWELL_KEY = ScopedValue.newInstance();

    /**
     * The main method that calls various API calls
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        whereAndGet();
        whereAndCall();
        whereAndRun();
        getWhere();
        callWhere();
        runWhere();
        doubleWhereAndCall();
        isBound();
        orElse();
    }

    /**
     * `where` returns a `Carrier`, which holds the mappings
     * for ScopedValue per thread.
     * `get` calls a `Supplier` of results, it in fact just get translated into a
     * `call`.
     */
    private static void whereAndGet() {
        String outerResult = ScopedValue.where(GREETING_KEY, "Hello").get(() -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var result = scope.fork(() -> STR."\{GREETING_KEY.get()}!");
                scope.join();
                return result.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(outerResult);
    }

    /**
     * `where` returns a `Carrier`, which holds the mappings
     * for ScopedValue per thread.
     * `call` takes a `Callable`
     */
    private static void whereAndCall() throws Exception {
        String outerResult = ScopedValue.where(GREETING_KEY, "Bon Jour").call(() -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var result = scope.fork(() -> STR."\{GREETING_KEY.get()}!");
                scope.join();
                return result.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(outerResult);
    }

    /**
     * `where` returns a `Carrier`, which holds the mappings
     * for ScopedValue per thread.
     * `run` takes a `Runnable`
     */
    private static void whereAndRun() {
        ScopedValue.where(GREETING_KEY, "Здравейте").run(printGreetingKey());
    }

    /**
     * Synonymous with where().get()
     */
    private static void getWhere() {
        String outerResult = ScopedValue.getWhere(GREETING_KEY, "Hola", () -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var result = scope.fork(() -> STR."\{GREETING_KEY.get()}!");
                scope.join();
                return result.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(outerResult);
    }

    /**
     * Synonymous with `where().call()`
     *
     * @throws Exception if an exception occurs during the execution of the code block
     */
    private static void callWhere() throws Exception {
        String outerResult = ScopedValue.callWhere(GREETING_KEY, "こんにちは", () -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var result = scope.fork(() -> STR."\{GREETING_KEY.get()}!");
                scope.join();
                return result.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(outerResult);
    }

    /**
     * Synonymous with `where().run()`
     */
    private static void runWhere() {
        ScopedValue.runWhere(GREETING_KEY, "नमस्ते", printGreetingKey());
    }

    @NotNull
    private static Runnable printGreetingKey() {
        return () -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var result = scope.fork(() -> STR."\{GREETING_KEY.get()}!");
                scope.join();
                System.out.println(result.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static void doubleWhereAndCall() throws Exception {
        String outerResult = ScopedValue.where(GREETING_KEY, "Γειά σου").where(FAREWELL_KEY, "Antio sas").call(() -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var helloSubtask = scope.fork(() -> STR."\{GREETING_KEY.get()}!");
                var goodbyeSubtask = scope.fork(() -> STR."\{FAREWELL_KEY.get()}!");
                scope.join();
                return STR."\{helloSubtask.get()} \{goodbyeSubtask.get()}";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(outerResult);
    }

    /**
     * You can use the `ScopedValue#isBound` to determine if the key is bound
     * at a position in your code
     *
     * @throws Exception when the call cannot complete
     */
    private static void isBound() throws Exception {
        String outerResult = ScopedValue.where(GREETING_KEY, "你好").call(() -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var helloSubtask = scope.fork(() -> STR."\{GREETING_KEY.get()}!");
                scope.join();
                GREETING_KEY.isBound();
                return STR."\{helloSubtask.get()}";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(outerResult);
    }

    /**
     * You can use the `ScopedValue#isBound` to determine if the key is bound
     * at a position in your code
     *
     * @throws Exception when the call cannot complete
     */
    private static void orElse() throws Exception {
        String outerResult = ScopedValue.where(GREETING_KEY, "Selamat datang").call(() -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                var helloSubtask = scope.fork(() -> STR."\{GREETING_KEY.orElse("Olá")}!");
                scope.join();
                return STR."\{helloSubtask.get()}";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(outerResult);
    }
}
