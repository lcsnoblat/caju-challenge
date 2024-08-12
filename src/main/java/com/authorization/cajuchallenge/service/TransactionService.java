package com.authorization.cajuchallenge.service;
import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;

import java.util.List;

public interface TransactionService  {
    CreateTransactionResponse authorize(Transaction transaction);
    Transaction createTransaction(Transaction transaction);
    Transaction getTransactionById(String id);
    List<Transaction> getAllTransactions();
}