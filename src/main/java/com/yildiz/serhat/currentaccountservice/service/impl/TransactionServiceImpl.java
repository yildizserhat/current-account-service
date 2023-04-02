package com.yildiz.serhat.currentaccountservice.service.impl;

import com.yildiz.serhat.currentaccountservice.domain.TransactionStatus;
import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import com.yildiz.serhat.currentaccountservice.domain.entity.Transaction;
import com.yildiz.serhat.currentaccountservice.domain.model.TransactionResponse;
import com.yildiz.serhat.currentaccountservice.repository.TransactionRepository;
import com.yildiz.serhat.currentaccountservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public TransactionResponse createNewTransaction(Account account, BigDecimal amount) {
        repository.save(Transaction.builder()
                .account(account)
                .amount(amount)
                .build());
        log.info("Transaction created for customerId: {}, accountId:{}", account.getCustomer().getId(), account.getId());
        return new TransactionResponse(TransactionStatus.APPROVED, amount);
    }
}
