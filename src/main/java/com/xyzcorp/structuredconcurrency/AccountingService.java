package com.xyzcorp.structuredconcurrency;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;
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
            Supplier<User> user = scope.fork(() -> userService.findUser(id));
            Supplier<List<Invoice>> order = scope.fork(() -> invoiceService.findAllInvoicesByUser(id));

            scope.join()            // Join both subtasks
                .throwIfFailed();  // ... and propagate errors

            // Here, both subtasks have succeeded, so compose their results
            return new UserInvoices(user.get(), order.get());
        }
    }

    /**
     * While you can return a `Supplier<T>` from fork, you may choose to bring in `Subtask`. Subtask
     * is a `Supplier` but it has extra APIs, like `state()` so you can do refined querying of the status
     * of your subtask. Notice that there is no `ExecutionException` anymore, since we are not throwing an exception
     * from our scope.
     *
     * @param id ID of the User
     * @return UserInvoices
     * @throws InterruptedException if the tasks are interrupted
     * @throws TimeoutException if the joinUntil is unable to complete
     */
    public Optional<UserInvoices> findAllInvoicesByUserUsingSubtask(Long id) throws InterruptedException, TimeoutException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            StructuredTaskScope.Subtask<User> user = scope.fork(() -> userService.findUser(id));
            StructuredTaskScope.Subtask<List<Invoice>> order = scope.fork(() -> invoiceService.findAllInvoicesByUser(id));

            // Join both subtasks but with a limit
            scope.joinUntil(Instant.now().plusSeconds(10));

            StructuredTaskScope.Subtask.State userState = user.state();
            StructuredTaskScope.Subtask.State orderState = order.state();

            if (userState.equals(StructuredTaskScope.Subtask.State.FAILED) ||
                orderState.equals(StructuredTaskScope.Subtask.State.FAILED)) {
                return Optional.empty();
            } else {
                // Here, both subtasks have succeeded, so compose their results
                return Optional.of(new UserInvoices(user.get(), order.get()));
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public UserInvoices findAllInvoicesByUserWithFailedUserService(long id) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<User> user = scope.fork(() -> userService.findUserError(id));
            Supplier<List<Invoice>> order = scope.fork(() -> invoiceService.findAllInvoicesByUser(id));

            scope.join()            // Join both subtasks
                .throwIfFailed();  // ... and propagate errors


            // Here, both subtasks have succeeded, so compose their results
            return new UserInvoices(user.get(), order.get());
        }
    }

    public UserInvoices findAllInvoicesByUserWithLatencyService(long id) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<User> user = scope.fork(() -> userService.findUserError(id));
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
            return switch (scope.join().result()) {
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
            return switch (scope.join().result()) {
                case User(var firstName, var lastName) -> String.format("User: %s %s", firstName, lastName);
                case List<?> list -> String.format("A list of %s", list);
                default -> "Unknown";
            };
        }
    }
}
