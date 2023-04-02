package com.yildiz.serhat.currentaccountservice.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountCreateRequestDTO(
        @NotNull Long customerId,
        @Positive
        @Digits(integer = 4, fraction = 2)
        @NotNull BigDecimal initialCredit
) {
}
