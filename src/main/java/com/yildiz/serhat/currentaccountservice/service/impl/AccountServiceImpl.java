package com.yildiz.serhat.currentaccountservice.service.impl;

import com.yildiz.serhat.currentaccountservice.controller.request.AccountCreateRequestDTO;
import com.yildiz.serhat.currentaccountservice.controller.response.AccountCreateResponseDTO;
import com.yildiz.serhat.currentaccountservice.domain.AccountStatus;
import com.yildiz.serhat.currentaccountservice.domain.TransactionStatus;
import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.TransactionResponse;
import com.yildiz.serhat.currentaccountservice.repository.AccountRepository;
import com.yildiz.serhat.currentaccountservice.service.AccountService;
import com.yildiz.serhat.currentaccountservice.service.CustomerService;
import com.yildiz.serhat.currentaccountservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final TransactionService transactionService;
    private final CustomerService customerService;
    private final AccountRepository repository;

    @Override
    public AccountCreateResponseDTO createAccount(AccountCreateRequestDTO requestDTO) {

        Customer customer = customerService.getCustomerById(requestDTO.customerId());
        Account newAccount = createNewAccount(customer);

        log.info("Account created for customerId  {}", requestDTO.customerId());

        TransactionResponse transactionResponse = new TransactionResponse(TransactionStatus.PENDING, ZERO);
        if (nonNull(requestDTO.initialCredit()) && requestDTO.initialCredit().compareTo(ZERO) > 0) {
            transactionResponse = transactionService.createNewTransaction(newAccount, requestDTO.initialCredit());
        }

        if (transactionResponse.transactionStatus().isApproved()) {
            updateAccount(newAccount, transactionResponse.amount());
        }

        return AccountCreateResponseDTO.builder()
                .accountId(newAccount.getId())
                .accountStatus(AccountStatus.CREATED)
                .balance(transactionResponse.amount())
                .message("Account Created Successfully")
                .build();
    }

    @Override
    public List<Account> getAllAccountsByCustomer(Long id) {
        log.info("Accounts are received for customer: {}", id);
        return repository.findByCustomerId(id);
    }

    private void updateAccount(Account newAccount, BigDecimal amount) {
        newAccount.setBalance(amount);
        repository.save(newAccount);
        log.info("Account updated with amount: {}, accountId: {}", amount, newAccount.getId());
    }

    private Account createNewAccount(Customer customer) {
        Account account = Account.builder()
                .customer(customer)
                .balance(ZERO)
                .build();
        return repository.save(account);
    }
}
