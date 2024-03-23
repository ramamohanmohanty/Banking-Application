package org.jt.BankingManagementSystem.repository;

import org.jt.BankingManagementSystem.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, String> {
}
