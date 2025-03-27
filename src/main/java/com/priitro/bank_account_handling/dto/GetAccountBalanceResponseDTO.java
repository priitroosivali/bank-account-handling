package com.priitro.bank_account_handling.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAccountBalanceResponseDTO {
    private List<AccountBalanceDTO> accountBalances;
}
