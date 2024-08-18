package com.authorization.cajuchallenge.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "accounts")
@Builder
public class Account {
    @Id
    private String id;
    private String name;
    private String email;
    private String accountId;
    private BalanceTypes balanceTypes;
}