package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.enums.MccCode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MerchantService {

    private final Map<String, MccCode> merchantMccMap = new HashMap<>();

    public MerchantService() {
        merchantMccMap.put("UBER TRIP                   SAO PAULO BR", MccCode.CASH_OTHER);
        merchantMccMap.put("UBER EATS                   SAO PAULO BR", MccCode.MEAL);
        merchantMccMap.put("PAG*JoseDaSilva          RIO DE JANEI BR", MccCode.FOOD);
        merchantMccMap.put("PICPAY*BILHETEUNICO           GOIANIA BR", MccCode.CASH_OTHER);
    }

    public MccCode getMccByMerchant(String merchantName, String defaultMcc) {
        return merchantMccMap.getOrDefault(merchantName, MccCode.valueOf(MccCode.fromString(defaultMcc)));
    }
}