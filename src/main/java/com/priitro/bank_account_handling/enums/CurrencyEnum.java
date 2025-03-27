package com.priitro.bank_account_handling.enums;

import lombok.Getter;

@Getter
public enum CurrencyEnum {
    USD("Dollar", 1.0),
    EUR("Euro", 1.1),
    SEK("Krona", 0.5),
    RUB("Ruble", 0.1);

    private final String fullName;
    private final double conversionRateToUsd;

    private CurrencyEnum(String fullName, double conversionRateToUsd) {
        this.fullName = fullName;
        this.conversionRateToUsd = conversionRateToUsd;
    }
}
