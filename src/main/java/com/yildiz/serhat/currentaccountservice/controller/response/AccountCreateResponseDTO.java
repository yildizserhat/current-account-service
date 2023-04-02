package com.yildiz.serhat.currentaccountservice.controller.response;

import com.yildiz.serhat.currentaccountservice.domain.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreateResponseDTO {
    private Long accountId;
    private BigDecimal balance;
    private AccountStatus accountStatus;
    private String message;
}
