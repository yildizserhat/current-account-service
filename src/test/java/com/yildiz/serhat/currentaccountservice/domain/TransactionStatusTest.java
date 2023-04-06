package com.yildiz.serhat.currentaccountservice.domain;

import org.junit.jupiter.api.Test;

import static com.yildiz.serhat.currentaccountservice.domain.TransactionStatus.APPROVED;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionStatusTest {

    @Test
    void shouldReturnTrueWhenTransactionStatusIsApproved() {
        assertTrue(APPROVED.isApproved());
    }
}