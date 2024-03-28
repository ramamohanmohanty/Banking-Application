package org.jt.BankingManagementSystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.domain.Account;
import org.jt.BankingManagementSystem.domain.Address;
import org.jt.BankingManagementSystem.dto.AccountResponseDTO;
import org.jt.BankingManagementSystem.repository.AccountRepo;
import org.jt.BankingManagementSystem.repository.AddressRepo;
import org.jt.BankingManagementSystem.service.IAccountService;
import org.jt.BankingManagementSystem.utility.RandomAccountNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepo accountRepo;
    private  final AddressRepo addressRepo;

    @Value("${upload.file.name}")
    private String uploadFileLocation;

    @Override
    public Account createAccount(Account account) {
        account.setAccountNumber(RandomAccountNumber.generate());
        return accountRepo.save(account);
    }

    @Override
    public Account saveAccount(Account account) {
        account.setAccountNumber(RandomAccountNumber.generate());
        var address = account.getAddress();
        address.setAccount(account);
        accountRepo.save(account);
        return account;
    }

    @Override
    public Account updateAccount(long accountNumber,Account account) {
        Account existingAccount = accountRepo.findByAccountNumber(accountNumber).orElseThrow();
        existingAccount.setAccountHolderName(account.getAccountHolderName());
        existingAccount.setAccountPhone(account.getAccountPhone());
        existingAccount.setAboutCustomer(account.getAboutCustomer());
        existingAccount.setAccountType(account.getAccountType());

        if(existingAccount.getAddress() != null){
            account.getAddress().setAccount(existingAccount);
            account.getAddress().setAddressId(existingAccount.getAddress().getAddressId());
            System.out.println(account.getAddress().getAddressId() + " " +
                    account.getAddress().getAccount().getAccountSerialNo());
        } else {
            existingAccount.setAddress(account.getAddress());
        }
        return accountRepo.save(existingAccount);
    }

    @Override
    public void depositBalance(long accountNumber, double accountBalance) {
        accountRepo.addBalance(accountNumber, accountBalance);
    }

    @Override
    public void withdrawBalance(long accountNumber, double accountBalance) {
        Account account = findAccountNumber(accountNumber);
        if (account.getAccountBalance() < accountBalance)
            throw new RuntimeException("Insufficient balance");

        accountRepo.deductBalance(accountNumber, accountBalance);
    }

    @Override
    public Account uploadProfilePicture(long accountNumber, MultipartFile file) throws FileNotFoundException, Exception {
        Account account = findAccountNumber(accountNumber);

        var fileName = file.getOriginalFilename();
        var extensionName = fileName.substring(fileName.lastIndexOf('.'));
        var name = fileName.substring(0,fileName.lastIndexOf('.'));
//        fileName = uploadFileLocation + name + "-" + System.currentTimeMillis() + extensionName;
        var fos = new FileOutputStream(fileName);
        fos.write(file.getBytes());
        account.setProfilePicture(fileName);
        accountRepo.save(account);
        fos.close();
        return account;
    }

    @Override
    public Account getAccountBySerialNumber(int serialNumber) {
        return accountRepo.findById(serialNumber).orElseThrow();
    }

    @Override
    public Account GetAccountByEmail(String email) {
        return accountRepo.findByEmail(email).orElseThrow();
    }

    @Override
    public Account findAccountNumber(long accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber).orElseThrow();
    }

    @Override
    public Account findByEmailAndPassword(String email, String password) {
        return accountRepo.findByCredentialAccountEmailAndCredentialAccountPassword(email, password).orElseThrow();
    }

    @Override
    public Account getAccountByAddressId(String addressId) {
        Address result = addressRepo.findById(addressId).orElseThrow();
        return result.getAccount();
    }

    @Override
    public List<Account> getAllAccounts() {
       return accountRepo.findAll();
    }

    @Override
    public Account deleteByAccountNumber(long accountNumber) {
//        accountRepo.deleteByAccountNumber(accountNumber);
        Account account = findAccountNumber(accountNumber);
        accountRepo.delete(account);
        return account;
    }


    @Override
    @Transactional
    public void transferBalance(long sender, long receiver, double balance) {
        //Account senderAccount = getAccount(sender);
        Boolean receiverAccount = accountRepo.existsByAccountNumber(receiver);
        if (!receiverAccount) {
            throw new RuntimeException("receiver account does not exist");
        }
        Account senderAccount = findAccountNumber(sender);
        double senderBalance = senderAccount.getAccountBalance();

        if (senderBalance < balance) {
            throw new RuntimeException("Insufficient fund");
        }
        accountRepo.addBalance(sender, balance);
        accountRepo.addBalance(receiver, balance);
    }

    @Override
    public byte[] getProfilePicture(long accountNumber) throws Exception {
        Account account = findAccountNumber(accountNumber);
        var fileLocation = account.getProfilePicture();

        if (fileLocation == null)
            throw new NoSuchElementException("Image not present");

        var fis = new FileInputStream(fileLocation);
        var image = fis.readAllBytes();
        fis.close();
        return image;
    }
}
