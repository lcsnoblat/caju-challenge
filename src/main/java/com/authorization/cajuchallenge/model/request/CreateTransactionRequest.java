package com.authorization.cajuchallenge.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    private String accountId;
    private BigDecimal totalAmount;
    private String mcc;
    private String merchant;
}
