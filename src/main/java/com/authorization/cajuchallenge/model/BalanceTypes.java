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

    public BigDecimal getBalanceByMcc(MccCode mccCode) {
        return switch (mccCode) {
            case FOOD -> getFood();
            case MEAL -> getMeal();
            default -> getCash();
        };
    }

    public void deductBalance(MccCode mccCode, BigDecimal amount) {
        switch (mccCode) {
            case FOOD:
                setFood(getFood().subtract(amount));
                break;
            case MEAL:
                setMeal(getMeal().subtract(amount));
                break;
            default:
                setCash(getCash().subtract(amount));
                break;
        }
    }
}