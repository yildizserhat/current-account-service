package com.yildiz.serhat.currentaccountservice.service;

import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import com.yildiz.serhat.currentaccountservice.domain.model.TransactionResponse;

import java.math.BigDecimal;

public interface TransactionService {

    TransactionResponse createNewTransaction(Account account, BigDecimal amount);
}
