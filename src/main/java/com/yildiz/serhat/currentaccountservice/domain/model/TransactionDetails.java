package com.yildiz.serhat.currentaccountservice.domain.model;

import com.yildiz.serhat.currentaccountservice.domain.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionDetails {
    private Long transactionId;
    private Long accountId;
    private BigDecimal amount;
    private BigDecimal accountBalance;


    public static TransactionDetails buildTransactionDetails(Transaction transaction) {
        return TransactionDetails.builder()
                .accountId(transaction.getAccount().getId())
                .amount(transaction.getAmount())
                .transactionId(transaction.getId())
                .accountBalance(transaction.getAccount().getBalance())
                .build();
    }
}
