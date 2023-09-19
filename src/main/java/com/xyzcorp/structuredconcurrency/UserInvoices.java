package com.xyzcorp.structuredconcurrency;

import java.util.List;

public record UserInvoices(User user, List<Invoice> invoices) {
}
