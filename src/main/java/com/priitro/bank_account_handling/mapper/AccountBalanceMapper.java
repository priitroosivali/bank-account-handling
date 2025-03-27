package com.priitro.bank_account_handling.mapper;

import com.priitro.bank_account_handling.dto.AccountBalanceDTO;
import com.priitro.bank_account_handling.dto.GetAccountBalanceResponseDTO;
import com.priitro.bank_account_handling.model.AccountBalance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountBalanceMapper {
    public AccountBalanceDTO toDto(AccountBalance accountBalance) {
        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO();
        accountBalanceDTO.setAccountNumber(accountBalance.getAccountNumber());
        accountBalanceDTO.setCurrency(accountBalance.getCurrency());
        accountBalanceDTO.setAmountInCents(accountBalance.getAmountInCents());
        return accountBalanceDTO;
    }

    public GetAccountBalanceResponseDTO toDto(List<AccountBalance> accountBalanceList) {
        GetAccountBalanceResponseDTO getAccountBalanceResponseDTO = new GetAccountBalanceResponseDTO();
        List<AccountBalanceDTO> accountBalanceDTOList = new ArrayList<>();
        for (AccountBalance accountBalance : accountBalanceList) {
            accountBalanceDTOList.add(toDto(accountBalance));
        }
        getAccountBalanceResponseDTO.setAccountBalances(accountBalanceDTOList);
        return getAccountBalanceResponseDTO;
    }
}
