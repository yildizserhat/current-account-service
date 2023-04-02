package com.yildiz.serhat.currentaccountservice.controller;

import com.yildiz.serhat.currentaccountservice.controller.request.AccountCreateRequestDTO;
import com.yildiz.serhat.currentaccountservice.controller.response.AccountCreateResponseDTO;
import com.yildiz.serhat.currentaccountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Account", description = "Endpoints about account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Create Account")
    public ResponseEntity<AccountCreateResponseDTO> createAccount(@RequestBody @Valid AccountCreateRequestDTO request) {
        accountService.createAccount(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
