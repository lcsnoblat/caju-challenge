package com.authorization.cajuchallenge.controller;

import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.model.enums.MccCode;
import com.authorization.cajuchallenge.model.request.CreateTransactionRequest;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;
import com.authorization.cajuchallenge.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/authorize")
    public CreateTransactionResponse authorize(@RequestBody CreateTransactionRequest transactionRequest) {
        try {
            Transaction transaction = Transaction.builder()
                    .mcc(MccCode.fromString(transactionRequest.getMcc()))
                    .merchant(transactionRequest.getMerchant())
                    .totalAmount(transactionRequest.getTotalAmount())
                    .accountId(transactionRequest.getAccountId())
                    .build();

            return transactionService.authorize(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}