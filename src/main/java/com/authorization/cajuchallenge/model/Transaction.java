package com.authorization.cajuchallenge.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String accountId;
    private BigDecimal totalAmount;
    private String mcc;
    private String merchant;
}