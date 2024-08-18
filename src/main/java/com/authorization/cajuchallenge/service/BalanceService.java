package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.BalanceTypes;
import com.authorization.cajuchallenge.model.enums.MccCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {

    public BigDecimal getBalanceByMcc(BalanceTypes balanceTypes, MccCode mccCode) {
        switch (mccCode) {
            case FOOD:
                return balanceTypes.getFood();
            case MEAL:
                return balanceTypes.getMeal();
            default:
                return balanceTypes.getCash();
        }
    }

    public void deductBalance(BalanceTypes balanceTypes, MccCode mccCode, BigDecimal amount) {
        switch (mccCode) {
            case FOOD:
                balanceTypes.setFood(balanceTypes.getFood().subtract(amount));
                break;
            case MEAL:
                balanceTypes.setMeal(balanceTypes.getMeal().subtract(amount));
                break;
            default:
                balanceTypes.setCash(balanceTypes.getCash().subtract(amount));
                break;
        }
    }
}