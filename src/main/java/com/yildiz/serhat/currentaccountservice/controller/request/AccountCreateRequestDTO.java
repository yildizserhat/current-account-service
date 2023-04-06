package com.yildiz.serhat.currentaccountservice.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountCreateRequestDTO(
        @NotNull(message = "CustomerId cannot be null") Long customerId,
        @Positive(message = "Amount must be positive")
        @Digits(integer = 4, fraction = 2, message = "Amount maximum could be 4 digits")
        @NotNull(message = "Amount cannot be null") BigDecimal initialCredit
) {
}
