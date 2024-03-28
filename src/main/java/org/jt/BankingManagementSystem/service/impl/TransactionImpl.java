package org.jt.BankingManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.constant.TransactionMode;
import org.jt.BankingManagementSystem.domain.Account;
import org.jt.BankingManagementSystem.domain.Transaction;
import org.jt.BankingManagementSystem.repository.AccountRepo;
import org.jt.BankingManagementSystem.repository.TransactionRepo;
import org.jt.BankingManagementSystem.service.ITransactionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransactionImpl implements ITransactionService {
    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;

    @Async
    @Transactional
    @Override
    public void addTransaction(Transaction transaction, long accountNumber) {
        var account = getAccount(accountNumber);
        transaction.setAccount(account);

        var transactions = account.getTransactions();
        if (transactions == null){
            transactions = new ArrayList<>();
        }
        transaction.setTimestamp(LocalDateTime.now());
        transactions.add(transaction);
        transactionRepo.save(transaction);
//        var account = accountRepo.findByAccountNumber(accountNumber).orElseThrow();
//        var transactions = account.getTransactions();
//
//        if (transactions == null) {
//            transactions = new ArrayList<>();
//        }
//
//        transaction.setTimestamp(LocalDateTime.now());
//        transactions.add(transaction);
//
//        transactionRepo.save(transaction);
    }

    @Async
    @Override
    public CompletableFuture<List<Transaction>> getDebitedTransactions(long accountNumber, int pageSize, int pageNumber) {
        var sort = Sort.by("timestamp").descending();
        var page = PageRequest.of(pageNumber - 1, pageSize, sort);

        var result = transactionRepo.findByModeAndAccountAccountNumber(TransactionMode.DEBIT,accountNumber,page);
        return CompletableFuture.completedFuture(result);
    }
//
//    @Override
//    public List<Transaction> getDebitedTransactions(long accountNumber) {
//        var sort = Sort.by("timestamp").descending();
//        var page = PageRequest.of(0,2, sort);
//
//        return transactionRepo
//                .findByModeAndAccountAccountNumber(TransactionMode.DEBIT,accountNumber, page);
//    }

    @Override
    public List<Transaction> getCreditedTransactions(long accountNumber, int pageSize, int pageNumber) {
        var sort = Sort.by("timestamp").descending();
        var page = PageRequest.of(pageNumber - 1, pageSize,sort);

        return transactionRepo
                .findByModeAndAccountAccountNumber(TransactionMode.CREDIT,accountNumber,page);
    }

    @Override
    public List<Transaction> getTransferedTransactions(long accountNumber, int pageSize, int pageNumber) {
        var sort = Sort.by("timestamp").descending();
        var page = PageRequest.of(pageNumber - 1, pageSize, sort);
        return transactionRepo.findByModeAndAccountAccountNumber(TransactionMode.TRANSFER,accountNumber,page);
    }

    @Override
    public long countRecord(TransactionMode mode, long accountNumber) {
        return transactionRepo.countByModeAndAccountAccountNumber(mode,accountNumber);
    }

    @Async
    @Override
    public CompletableFuture<Long> countRecord1(TransactionMode mode, long accountNumber) {
        var result = transactionRepo.countByModeAndAccountAccountNumber(mode,accountNumber);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    @Override
    public CompletableFuture<List<Transaction>> getLast5Transaction(long accountNumber) {
        var result = transactionRepo.findTop5ByAccountAccountNumberOrderByTimestampDesc(accountNumber);
        return CompletableFuture.completedFuture(result);
    }

//    @Override
//    public List<Transaction> getTransferedTransactions(long accountNumber) {
//        var sort = Sort.by("timestamp").descending();
//        var page = PageRequest.of(0,2, sort);
//        return transactionRepo
//                .findByModeAndAccountAccountNumber(TransactionMode.TRANSFER,accountNumber,page);
//    }

    private Account getAccount(long accountNumber){
        return accountRepo.findByAccountNumber(accountNumber).orElseThrow();
    }
}
