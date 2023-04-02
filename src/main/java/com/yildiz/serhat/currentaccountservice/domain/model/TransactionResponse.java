package com.yildiz.serhat.currentaccountservice.domain.model;

import com.yildiz.serhat.currentaccountservice.domain.TransactionStatus;

import java.math.BigDecimal;


public record TransactionResponse(TransactionStatus transactionStatus,
                                  BigDecimal amount) {
}
