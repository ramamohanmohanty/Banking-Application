package org.jt.BankingManagementSystem.service;

import org.jt.BankingManagementSystem.domain.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


public interface IAccountService {
    Account createAccount(Account account);
    Account saveAccount(Account account);

    Account updateAccount(long accountNumber,Account account);
    void depositBalance(long accountNumber,double accountBalance);
    void withdrawBalance(long accountNumber,double accountBalance);
    Account uploadProfilePicture(long accountNumber, MultipartFile file)throws FileNotFoundException,Exception;
    void transferBalance(long sender, long receiver, double balance);
    byte[] getProfilePicture(long accountNumber)throws Exception;
    Account getAccountBySerialNumber(int serialNumber);

    Account GetAccountByEmail(String email);
    Account findAccountNumber(long accountNumber);

    Account findByEmailAndPassword(String email, String password);
    Account getAccountByAddressId(String addressId);

    List<Account> getAllAccounts();

    Account deleteByAccountNumber(long accountNumber);

//    Optional<Account> getAccount(Long accountNumber);

}
