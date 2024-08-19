package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(String id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(String id, Account account) {
        if (accountRepository.existsById(id)) {
            account.setId(id);
            return accountRepository.save(account);
        } else {
            return null;
        }
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }
}