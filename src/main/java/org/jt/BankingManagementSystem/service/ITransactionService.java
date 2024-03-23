package org.jt.BankingManagementSystem.service;

import org.jt.BankingManagementSystem.domain.Transaction;

public interface ITransactionService {
    void addTransaction(Transaction transaction, long accountNumber);
}
