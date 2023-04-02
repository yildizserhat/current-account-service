package com.yildiz.serhat.currentaccountservice.service.impl;

import com.yildiz.serhat.currentaccountservice.domain.TransactionStatus;
import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.TransactionResponse;
import com.yildiz.serhat.currentaccountservice.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;


    @Test
    void shouldCreateNewTransaction() {
        Account account = Account.builder()
                .balance(BigDecimal.valueOf(5))
                .customer(Customer.builder().id(1L).build())
                .build();

        TransactionResponse newTransaction = transactionService.createNewTransaction(account, BigDecimal.valueOf(5));

        assertEquals(newTransaction.transactionStatus(), TransactionStatus.APPROVED);
        verify(transactionRepository, atLeastOnce()).save(any());
    }
}
