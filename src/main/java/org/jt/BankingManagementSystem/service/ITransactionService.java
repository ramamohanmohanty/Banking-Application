package org.jt.BankingManagementSystem.service;

import org.jt.BankingManagementSystem.constant.TransactionMode;
import org.jt.BankingManagementSystem.domain.Transaction;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITransactionService {
    void addTransaction(Transaction transaction, long accountNumber);

    CompletableFuture<List<Transaction>> getDebitedTransactions(long accountNumber, int pageSize, int pageNumber);
    List<Transaction> getCreditedTransactions(long accountNumber, int pageSize, int pageNumber);
    List<Transaction> getTransferedTransactions(long accountNumber, int pageSize, int pageNumber);

    long countRecord(TransactionMode mode, long accountNumber);

    CompletableFuture<Long> countRecord1(TransactionMode mode, long accountNumber);

    CompletableFuture<List<Transaction>> getLast5Transaction(long accountNumber);
}
