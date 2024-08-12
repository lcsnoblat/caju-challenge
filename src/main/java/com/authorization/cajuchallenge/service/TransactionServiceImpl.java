package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;
import com.authorization.cajuchallenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public CreateTransactionResponse authorize(Transaction transactionRequest) {

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