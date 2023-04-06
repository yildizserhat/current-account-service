package com.yildiz.serhat.currentaccountservice.service.impl;

import com.yildiz.serhat.currentaccountservice.controller.request.AccountCreateRequestDTO;
import com.yildiz.serhat.currentaccountservice.controller.response.AccountCreateResponseDTO;
import com.yildiz.serhat.currentaccountservice.domain.AccountStatus;
import com.yildiz.serhat.currentaccountservice.domain.TransactionStatus;
import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.TransactionResponse;
import com.yildiz.serhat.currentaccountservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;
    @Mock
    private AccountRepository repository;
    @Mock
    private CustomerServiceImpl customerService;
    @Mock
    private TransactionServiceImpl transactionService;

    @Test
    void shouldCreateAccount() {
        Customer customer = Customer.builder().id(1L).firstName("Serhat").lastName("Yildiz").build();
        TransactionResponse transactionResponse = new TransactionResponse(TransactionStatus.APPROVED, BigDecimal.valueOf(5));

        Account account1 = Account.builder().build();
        when(repository.save(any())).thenReturn(account1);
        when(transactionService.createNewTransaction(account1, BigDecimal.valueOf(10))).thenReturn(transactionResponse);
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        AccountCreateRequestDTO createRequestDTO = new AccountCreateRequestDTO(1L, BigDecimal.TEN);

        AccountCreateResponseDTO account = accountService.createAccount(createRequestDTO);

        verify(repository, times(2)).save(any());

        assertEquals(account.getBalance(), BigDecimal.valueOf(5));
        assertEquals(account.getAccountStatus(), AccountStatus.CREATED);
        assertEquals(account.getMessage(), "Account Created Successfully");
    }

    @Test
    void shouldGetAllAccountsByCustomer() {
        accountService.getAllAccountsByCustomer(1L);

        verify(repository, atLeastOnce()).findByCustomerId(1L);
    }
}