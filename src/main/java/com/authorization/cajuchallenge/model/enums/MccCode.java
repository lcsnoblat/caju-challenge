package com.authorization.cajuchallenge.model.enums;

import java.util.Arrays;

public enum MccCode {
    FOOD(new String[]{"5411", "5412"}),
    MEAL(new String[]{"5811", "5812"}),
    CASH_OTHER(new String[]{});

    private final String[] codes;

    MccCode(String[] codes) {
        this.codes = codes;
    }

    public String[] getCodes() {
        return codes;
    }

    public static MccCode fromString(String code) {
        return Arrays.stream(MccCode.values())
                .filter(mcc -> Arrays.asList(mcc.getCodes()).contains(code))
                .findFirst()
                .orElse(CASH_OTHER);
    }
}