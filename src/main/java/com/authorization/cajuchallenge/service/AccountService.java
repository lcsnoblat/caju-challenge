package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountById(String id);
    List<Account> getAllAccounts();
    Account updateAccount(String id, Account account);
    void deleteAccount(String id);
}