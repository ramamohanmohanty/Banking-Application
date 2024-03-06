package org.jt.BankingManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.jt.BankingManagementSystem.domain.Account;
import org.jt.BankingManagementSystem.service.IAccountService;
import org.jt.BankingManagementSystem.service.impl.AccountServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountController {
    private final IAccountService accountService;

    @PostMapping()
    public Account insertAccount(Account account) {
        return accountService.addAccount(account);
    }
}
