package com.priitro.bank_account_handling.utils;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.priitro.bank_account_handling.enums.CurrencyEnum;
import org.springframework.stereotype.Component;

@Component
public class StringToCurrencyEnumConverter extends StdConverter<String, CurrencyEnum> {
    @Override
    public CurrencyEnum convert(String source) {
        try {
            return CurrencyEnum.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
