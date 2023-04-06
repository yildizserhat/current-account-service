package com.yildiz.serhat.currentaccountservice.service;

import com.yildiz.serhat.currentaccountservice.controller.request.AccountCreateRequestDTO;
import com.yildiz.serhat.currentaccountservice.controller.response.AccountCreateResponseDTO;
import com.yildiz.serhat.currentaccountservice.domain.entity.Account;

import java.util.List;

public interface AccountService {

    AccountCreateResponseDTO createAccount(AccountCreateRequestDTO requestDTO);

    List<Account> getAllAccountsByCustomer(Long id);
}
