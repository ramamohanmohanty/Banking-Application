package org.jt.BankingManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.domain.Account;
import org.jt.BankingManagementSystem.repository.AccountRepo;
import org.jt.BankingManagementSystem.service.IAccountService;
import org.jt.BankingManagementSystem.utility.RandomAccountNumber;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepo accountRepo;

    @Override
    public Account addAccount(Account account) {
        account.setAccountNumber(RandomAccountNumber.generate());
        return accountRepo.save(account);
    }

    @Override
    public Account updateAccount(Long accountNumber) {
        return null;
    }

    @Override
    public Account deleteAccount(Long accountNumber) {
        return null;
    }

    @Override
    public Account getAccount(Long accountNumber) {
        return null;
    }

    @Override
    public Account getAccountBySerialNumber(int serialNumber) {
        return null;
    }

    @Override
    public Account GetAccountByEmail(String email) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }
}
