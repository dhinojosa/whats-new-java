package com.xyzcorp.structuredconcurrency;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public class AccountingService {
    private final UserService userService;
    private final InvoiceService invoiceService;

    public AccountingService(UserService userService, InvoiceService invoiceService) {
        this.userService = userService;
        this.invoiceService = invoiceService;
    }

    public UserInvoices findAllInvoicesByUser(Long id) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<User> user  = scope.fork(() -> userService.findUser(id));
            Supplier<List<Invoice>> order = scope.fork(() -> invoiceService.findAllInvoicesByUser(id));

            scope.join()            // Join both subtasks
                .throwIfFailed();  // ... and propagate errors

            // Here, both subtasks have succeeded, so compose their results
            return new UserInvoices(user.get(), order.get());
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public UserInvoices findAllInvoicesByUserWithFailedUserService(long id) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<User> user  = scope.fork(() -> userService.findUserError(id));
            Supplier<List<Invoice>> order = scope.fork(() -> invoiceService.findAllInvoicesByUser(id));

            scope.join()            // Join both subtasks
                .throwIfFailed();  // ... and propagate errors

            // Here, both subtasks have succeeded, so compose their results
            return new UserInvoices(user.get(), order.get());
        }
    }

    public UserInvoices findAllInvoicesByUserWithLatencyService(long id) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<User> user  = scope.fork(() -> userService.findUserError(id));
            Supplier<List<Invoice>> order = scope.fork(() -> invoiceService.findAllInvoicesByUserLongTime(id));

            scope.join()            // Join both subtasks
                .throwIfFailed();  // ... and propagate errors

            // Here, both subtasks have succeeded, so compose their results
            return new UserInvoices(user.get(), order.get());
        }
    }

    public String findAllEitherUserOrInvoices(long id) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
            scope.fork(() -> userService.findUser(id));
            scope.fork(() -> invoiceService.findAllInvoicesByUserLongTime(id));
            return switch(scope.join().result()) {
                case User(var firstName, var lastName) -> String.format("User: %s %s", firstName, lastName);
                case List<?> list -> String.format("A list of %s", list);
                default -> "Unknown";
            };
        }
    }

    public String findAllEitherUserOrInvoicesFromUserServiceWithLatency(long id) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
            scope.fork(() -> userService.findUserLongTime(id));
            scope.fork(() -> invoiceService.findAllInvoicesByUser(id));
            return switch(scope.join().result()) {
                case User(var firstName, var lastName) -> String.format("User: %s %s", firstName, lastName);
                case List<?> list -> String.format("A list of %s", list);
                default -> "Unknown";
            };
        }
    }
}
