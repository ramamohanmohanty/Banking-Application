package org.jt.BankingManagementSystem.dto;

import org.jt.BankingManagementSystem.constant.TransactionMode;

import java.time.LocalDateTime;

public record TransactionDTO(
        LocalDateTime timeStamp,
        TransactionMode mode,
        double accountBalance
) {
}
