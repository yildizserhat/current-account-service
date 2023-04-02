package com.yildiz.serhat.currentaccountservice.repository;

import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long id);
}
