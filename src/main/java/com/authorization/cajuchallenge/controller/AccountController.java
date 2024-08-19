package com.authorization.cajuchallenge.controller;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable String id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}