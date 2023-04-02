package com.yildiz.serhat.currentaccountservice.domain;

public enum TransactionStatus {
    PENDING, CANCELLED, APPROVED;

    public boolean isApproved() {
        return TransactionStatus.APPROVED.equals(this);
    }
}
