package org.jt.BankingManagementSystem.dto;

import java.util.List;

public record DashboardDTO (
        long numberOfDeposit,
        long numberOfWithdrawal,
        long numberOfTransfer,
        List<TransactionDTO> transactions
) {
}
