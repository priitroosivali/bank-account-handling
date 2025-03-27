package com.priitro.bank_account_handling.utils;

import com.priitro.bank_account_handling.enums.CurrencyEnum;
import org.apache.commons.lang3.EnumUtils;

public class CurrencyEnumUtil {
    public static CurrencyEnum convertCurrencyToEnum(String currency) {
        if (EnumUtils.isValidEnumIgnoreCase(CurrencyEnum.class, currency)) {
            return EnumUtils.getEnumIgnoreCase(CurrencyEnum.class, currency);
        }
        return null;
    }
}
