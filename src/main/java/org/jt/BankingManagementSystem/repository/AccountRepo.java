package org.jt.BankingManagementSystem.repository;

import org.jt.BankingManagementSystem.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
