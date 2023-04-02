package com.yildiz.serhat.currentaccountservice.repository;

import com.yildiz.serhat.currentaccountservice.domain.entity.Transaction;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
