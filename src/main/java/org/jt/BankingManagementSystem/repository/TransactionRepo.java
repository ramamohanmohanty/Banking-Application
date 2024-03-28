package org.jt.BankingManagementSystem.repository;

import org.jt.BankingManagementSystem.constant.TransactionMode;
import org.jt.BankingManagementSystem.domain.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, String> {
    List<Transaction>
    findByModeAndAccountAccountNumber(TransactionMode mode, long accountNumber,
                                      Pageable pageable);

    long countByModeAndAccountAccountNumber(TransactionMode mode, long accountNumber);

    List<Transaction> findTop5ByAccountAccountNumberOrderByTimestampDesc(long accountNumber);
}
