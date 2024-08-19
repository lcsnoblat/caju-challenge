package com.authorization.cajuchallenge.model;

import com.authorization.cajuchallenge.model.enums.MccCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BalanceTypes {
    private BigDecimal food = BigDecimal.ZERO;
    private BigDecimal meal = BigDecimal.ZERO;
    private BigDecimal cash = BigDecimal.ZERO;

}