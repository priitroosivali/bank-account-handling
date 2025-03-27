package com.priitro.bank_account_handling.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.priitro.bank_account_handling.utils.StringToCurrencyEnumConverter;
import com.priitro.bank_account_handling.enums.CurrencyEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeCurrencyRequestDTO {
    private String accountNumber;
    @JsonDeserialize(converter = StringToCurrencyEnumConverter.class)
    private CurrencyEnum currencyFrom;
    @JsonDeserialize(converter = StringToCurrencyEnumConverter.class)
    private CurrencyEnum currencyTo;
    private Long amountInCents;
}
