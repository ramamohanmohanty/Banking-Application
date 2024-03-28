package org.jt.BankingManagementSystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.domain.Account;
import org.jt.BankingManagementSystem.domain.Credential;
import org.jt.BankingManagementSystem.dto.*;
import org.jt.BankingManagementSystem.mapper.AccountMapper;
import org.jt.BankingManagementSystem.service.IAccountService;
import org.jt.BankingManagementSystem.service.IJWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final IAccountService accountService;
    private final IJWTService jwtService;

    @PostMapping("/register")
//    @PostMapping("/saveAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
        Account account = AccountMapper.modelMapper(accountRequestDTO);
        Account result = accountService.createAccount(account);
        return AccountMapper.dtoMapper(result);
    }

    @PostMapping("/saveAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO insertAccount(@Valid @RequestBody AccountAddressDTO accountAddressDTO) {
        Account account = AccountMapper.modelMapper(accountAddressDTO);
        Account accountResponse = accountService.saveAccount(account);
        return AccountMapper.dtoMapper(accountResponse);
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO accountByEmailAndPassword(@RequestBody Credential credential){
        Account account = accountService.findByEmailAndPassword(credential.getAccountEmail(), credential.getAccountPassword());
        var token = jwtService.generateToken(String.valueOf(account.getAccountNumber()));
//        return AccountMapper.dtoMapper(account);
        return new TokenDTO(token);
    }
//    public AccountResponseDTO accountByEmailAndPassword(@RequestBody Credential credential){
//        Account account = accountService.findByEmailAndPassword(credential.getAccountEmail(), credential.getAccountPassword());
//        return AccountMapper.dtoMapper(account);
//    }

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO uploadPicture(/*@RequestHeader*/ @RequestAttribute long accountNumber, @RequestParam("file")MultipartFile file) throws Exception {
        if(file.isEmpty())
            throw new RuntimeException("Image not Found");

        var result = accountService.uploadProfilePicture(accountNumber, file);
            return AccountMapper.dtoMapper(result);

    }

    @PutMapping("/update/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponseDTO updatedByAccountNumber(@PathVariable long accountNumber,@RequestBody AccountUpdateDTO accountUpdateDTO){
        var account = AccountMapper.modelMapper(accountUpdateDTO);
        var result = accountService.updateAccount(accountNumber,account);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping(value = "/{accountNumber}/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable long accountNumber) throws Exception{
        return accountService.getProfilePicture(accountNumber);
    }

    @GetMapping("/getAllAccount")
    public List<AccountResponseDTO> getAccounts() {
        List<Account> results = accountService.getAllAccounts();
        return results.stream().map(AccountMapper::dtoMapper).toList();
    }

    @GetMapping("/serialNumber")
    public AccountResponseDTO retriveById(@PathVariable int serialNumber){
        Account account = accountService.getAccountBySerialNumber(serialNumber);
        return AccountMapper.dtoMapper(account);
    }
    @GetMapping("/email/{email}")
    public AccountResponseDTO retriveByEmail(@RequestParam String email){
        Account account = accountService.GetAccountByEmail(email);
        return AccountMapper.dtoMapper(account);
    }


//    @GetMapping("/accountNumber")
//    public Optional<Account> getAccountNumber(@PathVariable Long accountNumber){
//       return accountService.getAccount(accountNumber);
//    }
    @GetMapping("/number/{accountNumber}")
    public AccountResponseDTO accountByNumber(@PathVariable long accountNumber){
        var result = accountService.findAccountNumber(accountNumber);
        return AccountMapper.dtoMapper(result);
    }
    @GetMapping("/current")
    public AccountResponseDTO currentAccountByNumber(/*@PathVariable*/ @RequestAttribute long accountNumber){
        var result = accountService.findAccountNumber(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/address-details/{addressId}")
    public AccountResponseDTO accountByAddressId(@PathVariable String addressId){
        var result = accountService.getAccountByAddressId(addressId);
        return AccountMapper.dtoMapper(result);
    }

    @PatchMapping("/deposit/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(/*@RequestHeader*/@RequestAttribute long accountNumber, @PathVariable("balance") double accountBalance) {
        accountService.depositBalance(accountNumber, accountBalance);
    }

    @PatchMapping("/withdraw/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdrawMoney(/*@RequestHeader*/ @RequestAttribute long accountNumber, @PathVariable("amount") double accountBalance) {
        accountService.withdrawBalance(accountNumber, accountBalance);
    }

    @PatchMapping("/transfer/{receiver}/balance/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferByAccount(/*@RequestHeader*/ @RequestAttribute long sender, @PathVariable long receiver, @PathVariable double balance) {
        accountService.transferBalance(sender, receiver, balance);
    }

    @DeleteMapping("/delete/{accountNumber}")
    public AccountResponseDTO deleteAccount(@PathVariable long accountNumber){
        var result = accountService.deleteByAccountNumber(accountNumber);
        return AccountMapper.dtoMapper(result);
    }
//
//    public Account deleteAccount(@RequestHeader long accountNumber){
//        return accountService.deleteAccount(accountNumber);
//    }
}
