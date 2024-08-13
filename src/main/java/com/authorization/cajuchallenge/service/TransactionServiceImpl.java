package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.model.enums.MccCode;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;
import com.authorization.cajuchallenge.repository.AccountRepository;
import com.authorization.cajuchallenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public CreateTransactionResponse authorize(Transaction transactionRequest) {
        Account account = accountRepository.findByAccountId(transactionRequest.getAccountId()).orElse(null);
        BigDecimal amount = transactionRequest.getTotalAmount();

        if (account == null) {
            return new CreateTransactionResponse("No account");
        }

        if (account.getBalance().compareTo(amount) >= 0) {
            CompletableFuture.runAsync(() -> {
                account.setBalance(account.getBalance().subtract(amount));
                accountRepository.save(account);
                transactionRepository.save(transactionRequest);
            });

            return new CreateTransactionResponse("Success");
        }

        return new CreateTransactionResponse("Fail");
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}