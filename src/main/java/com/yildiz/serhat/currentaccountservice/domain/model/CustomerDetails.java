package com.yildiz.serhat.currentaccountservice.domain.model;

import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetails {
    private String firstName;
    private String lastName;
    private List<Account> accounts;
}
