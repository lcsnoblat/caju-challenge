package com.authorization.cajuchallenge.controller;

import com.authorization.cajuchallenge.model.request.CreateTransactionRequest;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;
import com.authorization.cajuchallenge.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping("/authorize")
    public CreateTransactionResponse authorizeTransaction(@RequestBody CreateTransactionRequest transactionRequest) {
        CreateTransactionResponse response = TransactionService.authorize(transactionRequest);

        return new CreateTransactionResponse(response.getCode());
    }
}