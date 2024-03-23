package org.jt.BankingManagementSystem.mapper;

import lombok.NoArgsConstructor;
import org.jt.BankingManagementSystem.constant.AccountType;
import org.jt.BankingManagementSystem.domain.Account;
import org.jt.BankingManagementSystem.domain.Address;
import org.jt.BankingManagementSystem.domain.Credential;
import org.jt.BankingManagementSystem.dto.AccountAddressDTO;
import org.jt.BankingManagementSystem.dto.AccountRequestDTO;
import org.jt.BankingManagementSystem.dto.AccountResponseDTO;
import org.jt.BankingManagementSystem.dto.AccountUpdateDTO;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class AccountMapper {
    public static Account modelMapper(AccountAddressDTO accountAddressDTO) {
        var credential = new Credential();
        BeanUtils.copyProperties(accountAddressDTO, credential);

        var address = new Address();
        BeanUtils.copyProperties(accountAddressDTO, address);

        var account = new Account();
        BeanUtils.copyProperties(accountAddressDTO, account);

        account.setCredential(credential);
        account.setAddress(address);

        return account;
    }
    public static Account modelMapper(AccountUpdateDTO accountUpdateDTO) {
        var address = new Address();
        BeanUtils.copyProperties(accountUpdateDTO, address);

        var account = new Account();
        BeanUtils.copyProperties(accountUpdateDTO, account);

        account.setAddress(address);

        return account;
    }

    public static Account modelMapper(AccountRequestDTO accountRequestDTO){
//        account.setAccountHolderName(accountRequestDTO.accountHolderName());
//        account.setCredential(accountRequestDTO.accountEmail());
//        account.setCredential(accountRequestDTO.accountBalance());
//        account.setAccountPhone(accountRequestDTO.accountPhone());
//        account.setAboutCustomer(accountRequestDTO.aboutCustomer());
//        account.setAccountType(accountRequestDTO.accountType());
//        return account;

        Credential credential = new Credential();
        BeanUtils.copyProperties(accountRequestDTO, credential);

        Account account = new Account();
        BeanUtils.copyProperties(accountRequestDTO,account);
        account.setCredential(credential);

        return account;
    }

    public static AccountResponseDTO dtoMapper(Account account) {
//        long accountNumber = account.getAccountNumber();
//        String accountHolderName = account.getAccountHolderName();
//        String accountPhone = account.getAccountPhone();
//        String aboutCustomer = account.getAboutCustomer();
//        double accountBalance = account.getAccountBalance();
//        AccountEnum accountType = account.getAccountType();

        var credential = account.getCredential();
        var address = account.getAddress() == null ?
                new Address() : account.getAddress();
//        String accountEmail = credential.getAccountEmail();

        return new AccountResponseDTO(
                account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getAboutCustomer(),
                credential.getAccountEmail(),
                account.getAccountPhone(),
                account.getAccountBalance(),
                account.getAccountType(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZipcode()
        );
//         accountNumber,
//         accountHolderName,
//         accountEmail,
//         accountPhone,
//         aboutCustomer,
//         accountBalance,
//         accountType );
    }
}


/* account.setAccountHolderName(dto.accountHolderName());
        BeanUtils.copyProperties(dto,account);
        account.setContactNumber(dto.contactNumber());
        account.setAboutCustomer(dto.aboutCustomer());
        account.setAccountType(dto.accountType());*/

       /* credential.setAccountEmail(dto.accountEmail());
        credential.setaccountBalance(dto.accountBalance());*/