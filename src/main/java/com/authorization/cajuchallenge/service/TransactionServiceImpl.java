package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.model.request.CreateTransactionRequest;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;
import com.authorization.cajuchallenge.repository.TransactionCommandRepository;
import com.authorization.cajuchallenge.repository.TransactionQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    private final TransactionCommandRepository transactionCommandRepository;
    private final TransactionQueryRepository transactionQueryRepository;

    public TransactionServiceImpl(TransactionCommandRepository transactionCommandRepository, TransactionQueryRepository transactionQueryRepository) {
        this.transactionCommandRepository = transactionCommandRepository;
        this.transactionQueryRepository = transactionQueryRepository;
    }

    @Override
    public CreateTransactionResponse authorize(CreateTransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionCommandRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(String id) {
        return transactionQueryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionQueryRepository.findAll(); // Lê do banco de consulta
    }
}