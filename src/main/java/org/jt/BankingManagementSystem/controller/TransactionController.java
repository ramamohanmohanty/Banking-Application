package org.jt.BankingManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.constant.TransactionMode;
import org.jt.BankingManagementSystem.dto.DatatableDTO;
import org.jt.BankingManagementSystem.dto.TransactionDTO;
import org.jt.BankingManagementSystem.mapper.TransactionMapper;
import org.jt.BankingManagementSystem.service.ITransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

//    @GetMapping("/{accountNumber}/credit") //this code is a spring autometic thread creation
//    public List<TransactionDTO> creditedTransaction(@PathVariable long accountNumber,
//                                                    @RequestParam(required = false, defaultValue = "1") int pageNumber,
//                                                    @RequestParam(required = false, defaultValue = "10")int pageSize){
//        var result = transactionService.getCreditedTransactions(accountNumber, pageNumber, pageSize);
//        return result.stream().map(TransactionMapper::dtoMapper).toList();
//    }
    @GetMapping("/credit") //this code is a manually thread creation on developer
    public DatatableDTO creditedTransaction(/*@RequestHeader*/ @RequestAttribute  long accountNumber,
                                            @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize){
        long startTime = System.currentTimeMillis();
        var results = transactionService.getCreditedTransactions(accountNumber,pageNumber,pageSize);
        var transactions = results.stream().map(TransactionMapper::dtoMapper).toList();
        var totalRecord = transactionService.countRecord(TransactionMode.CREDIT,accountNumber);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
        return new DatatableDTO(totalRecord,pageNumber,pageSize,transactions);
    }
    @GetMapping("/debit")
    public DatatableDTO debitedTransaction(/*@RequestHeader*/ @RequestAttribute long accountNumber,
                                            @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize)
            throws Exception{
        var results = transactionService.getDebitedTransactions(accountNumber,pageNumber,pageSize);
        var transactions = results.thenApplyAsync(result -> result.stream().map(TransactionMapper::dtoMapper).toList());

        var totalRecord = transactionService.countRecord1(TransactionMode.DEBIT,accountNumber);
        CompletableFuture.allOf(results,transactions,totalRecord).join();

        return new DatatableDTO(totalRecord.get(),pageNumber,pageSize,transactions.get());
    }

    @GetMapping("/transfer")
    public DatatableDTO transferedTransaction(/*@RequestHeader*/ @RequestAttribute long accountNumber,
                                            @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize){
        var results = transactionService.getTransferedTransactions(accountNumber,pageNumber,pageSize);
        var transactions = results.stream().map(TransactionMapper::dtoMapper).toList();
        var totalRecord = transactionService.countRecord(TransactionMode.TRANSFER,accountNumber);
        return new DatatableDTO(totalRecord,pageNumber,pageSize,transactions);
    }
//    @GetMapping("{accountNumber}/debit")
//    public List<TransactionDTO> debitedTransactions(@PathVariable long accountNumber){
//        var result = transactionService.getDebitedTransactions(accountNumber);
//        return result.stream().map(TransactionMapper::dtoMapper).toList();
//    }

//    @GetMapping("/{accountNumber}/transfer")
//    public List<TransactionDTO> transferedTransactions(@PathVariable long accountNumber){
//        var result = transactionService.getTransferedTransactions(accountNumber);
//        return result.stream().map(TransactionMapper::dtoMapper).toList();
//    }
}
