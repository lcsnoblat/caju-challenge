package com.authorization.cajuchallenge.model.request;

import com.authorization.cajuchallenge.model.decorators.NotZero;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    @NotNull(message = "Total amount cannot be null")
    @NotZero(message = "Total amount must not be zero")
    private BigDecimal totalAmount;

    @NotNull(message = "Account ID cannot be null")
    private String accountId;
    private String mcc;
    private String merchant;
}
