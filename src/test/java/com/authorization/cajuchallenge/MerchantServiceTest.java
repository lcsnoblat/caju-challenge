package com.authorization.cajuchallenge;

import com.authorization.cajuchallenge.model.enums.MccCode;
import com.authorization.cajuchallenge.service.MerchantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantServiceTest {

    private MerchantService merchantService;

    @BeforeEach
    void setUp() {
        merchantService = new MerchantService();
    }

    @Test
    void testGetMccByMerchant() {
        assertEquals(MccCode.MEAL, merchantService.getMccByMerchant("UBER EATS                   SAO PAULO BR", "0000"));
        assertEquals(MccCode.FOOD, merchantService.getMccByMerchant("PAG*JoseDaSilva          RIO DE JANEI BR", "0000"));
        assertEquals(MccCode.CASH_OTHER, merchantService.getMccByMerchant("UNKNOWN MERCHANT", "0000"));
    }
}