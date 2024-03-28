package org.jt.BankingManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.constant.TransactionMode;
import org.jt.BankingManagementSystem.dto.DashboardDTO;
import org.jt.BankingManagementSystem.mapper.TransactionMapper;
import org.jt.BankingManagementSystem.service.ITransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/dashboards")
@RequiredArgsConstructor
public class DashboardController {
    private final ITransactionService transactionService;

    @GetMapping
    public DashboardDTO dashboardDetails(@RequestAttribute long accountNumber) throws ExecutionException, InterruptedException {
        var numberOfDeposit = transactionService.countRecord1(TransactionMode.CREDIT, accountNumber);
        var numberOfWithdraw = transactionService.countRecord1(TransactionMode.DEBIT, accountNumber);
        var numberOfTransfer = transactionService.countRecord1(TransactionMode.TRANSFER, accountNumber);

        var results = transactionService.getLast5Transaction(accountNumber);
        var transactions = results.thenApplyAsync(result -> result.stream().map(TransactionMapper::dtoMapper).toList());

        CompletableFuture.allOf(numberOfDeposit, numberOfWithdraw, numberOfTransfer, results,transactions).join();

        return new DashboardDTO(
                numberOfDeposit.get(),
                numberOfWithdraw.get(),
                numberOfTransfer.get(),
                transactions.get()
        );
    }
}
