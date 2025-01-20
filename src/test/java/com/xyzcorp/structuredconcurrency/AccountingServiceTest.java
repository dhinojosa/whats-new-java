package com.xyzcorp.structuredconcurrency;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class AccountingServiceTest {

    @Test
    void testStructuredConcurrency() throws ExecutionException, InterruptedException {
        UserService userService = new UserService();
        InvoiceService invoiceService = new InvoiceService();
        AccountingService accountingService = new AccountingService(userService, invoiceService);
        UserInvoices userInvoice = accountingService.findAllInvoicesByUser(90L);
        Assertions.assertThat(userInvoice.invoices()).hasSize(3);
        Assertions.assertThat(userInvoice.user().firstName()).isEqualTo("Simon");
        Assertions.assertThat(userInvoice.user().lastName()).isEqualTo("Roberts");
    }

    @Test
    void testStructuredConcurrencyWithSubtasks() throws TimeoutException, InterruptedException {
        UserService userService = new UserService();
        InvoiceService invoiceService = new InvoiceService();
        AccountingService accountingService = new AccountingService(userService, invoiceService);
        Optional<UserInvoices> userInvoiceOptional = accountingService.findAllInvoicesByUserUsingSubtask(90L);
        Assertions.assertThat(userInvoiceOptional).isNotEmpty();
        UserInvoices userInvoices = userInvoiceOptional.get();
        Assertions.assertThat(userInvoices.invoices()).hasSize(3);
        User user = userInvoices.user();
        Assertions.assertThat(user.firstName()).isEqualTo("Simon");
        Assertions.assertThat(user.lastName()).isEqualTo("Roberts");
    }

    @Test
    void testStructuredConcurrencyWithError() {
        UserService userService = new UserService();
        InvoiceService invoiceService = new InvoiceService();
        AccountingService accountingService = new AccountingService(userService, invoiceService);
        Assertions.assertThatThrownBy(() -> accountingService.findAllInvoicesByUserWithFailedUserService(90L))
            .isInstanceOf(ExecutionException.class);
    }

    @Test
    void testStructuredConcurrencyWithErrorAndLongerInvoiceService() {
        UserService userService = new UserService();
        InvoiceService invoiceService = new InvoiceService();
        AccountingService accountingService = new AccountingService(userService, invoiceService);
        Assertions.assertThatThrownBy(() -> accountingService.findAllInvoicesByUserWithFailedUserService(90L))
            .isInstanceOf(ExecutionException.class);
    }

    @Test
    void testStructuredConcurrencyWithOnSuccess() throws ExecutionException, InterruptedException {
        UserService userService = new UserService();
        InvoiceService invoiceService = new InvoiceService();
        AccountingService accountingService = new AccountingService(userService, invoiceService);
        String result = accountingService.findAllEitherUserOrInvoices(90L);
        Assertions.assertThat(result).isEqualTo("User: Simon Roberts");
    }

    @Test
    void testStructuredConcurrencyWithOnSuccessWithUserServiceLatency() throws ExecutionException, InterruptedException {
        UserService userService = new UserService();
        InvoiceService invoiceService = new InvoiceService();
        AccountingService accountingService = new AccountingService(userService, invoiceService);
        String result = accountingService.findAllEitherUserOrInvoicesFromUserServiceWithLatency(90L);
        String expected = "A list of [Invoice[number=402, amount=1120.0], Invoice[number=1402, amount=1220.0], Invoice[number=671, amount=1220.0]]";
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
