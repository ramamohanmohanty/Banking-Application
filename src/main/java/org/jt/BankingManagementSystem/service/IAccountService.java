package org.jt.BankingManagementSystem.service;

import org.jt.BankingManagementSystem.domain.Account;
import java.util.List;


public interface IAccountService {
    Account addAccount(Account account);

    Account updateAccount(Long accountNumber);

    Account deleteAccount(Long accountNumber);

    Account getAccount(Long accountNumber);
    Account getAccountBySerialNumber(int serialNumber);

    Account GetAccountByEmail(String email);

    List<Account> getAllAccounts();


}
