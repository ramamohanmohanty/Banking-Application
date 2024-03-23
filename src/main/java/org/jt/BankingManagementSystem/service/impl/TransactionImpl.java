package org.jt.BankingManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.domain.Transaction;
import org.jt.BankingManagementSystem.repository.AccountRepo;
import org.jt.BankingManagementSystem.repository.TransactionRepo;
import org.jt.BankingManagementSystem.service.ITransactionService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TransactionImpl implements ITransactionService {
    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;

    @Async
    @Override
    public void addTransaction(Transaction transaction, long accountNumber) {
        var account = accountRepo.findByAccountNumber(accountNumber).orElseThrow();
        var transactions = account.getTransactions();

        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        transaction.setTimestamp(LocalDateTime.now());
        transactions.add(transaction);

        transactionRepo.save(transaction);
    }
}
