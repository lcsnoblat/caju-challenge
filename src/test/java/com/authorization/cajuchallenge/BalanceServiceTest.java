package com.authorization.cajuchallenge;

import com.authorization.cajuchallenge.model.BalanceTypes;
import com.authorization.cajuchallenge.model.enums.MccCode;
import com.authorization.cajuchallenge.service.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {

    private BalanceService balanceService;
    private BalanceTypes balanceTypes;

    @BeforeEach
    void setUp() {
        balanceService = new BalanceService();
        balanceTypes = new BalanceTypes();
        balanceTypes.setFood(new BigDecimal("500.00"));
        balanceTypes.setMeal(new BigDecimal("300.00"));
        balanceTypes.setCash(new BigDecimal("200.00"));
    }

    @Test
    void testGetBalanceByMcc() {
        assertEquals(new BigDecimal("500.00"), balanceService.getBalanceByMcc(balanceTypes, MccCode.FOOD));
        assertEquals(new BigDecimal("300.00"), balanceService.getBalanceByMcc(balanceTypes, MccCode.MEAL));
        assertEquals(new BigDecimal("200.00"), balanceService.getBalanceByMcc(balanceTypes, MccCode.CASH_OTHER));
    }

    @Test
    void testDeductBalance() {
        balanceService.deductBalance(balanceTypes, MccCode.FOOD, new BigDecimal("100.00"));
        assertEquals(new BigDecimal("400.00"), balanceTypes.getFood());

        balanceService.deductBalance(balanceTypes, MccCode.MEAL, new BigDecimal("50.00"));
        assertEquals(new BigDecimal("250.00"), balanceTypes.getMeal());

        balanceService.deductBalance(balanceTypes, MccCode.CASH_OTHER, new BigDecimal("200.00"));
        assertEquals(new BigDecimal("0.00"), balanceTypes.getCash());
    }
}