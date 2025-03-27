package com.priitro.bank_account_handling.repository;

import com.priitro.bank_account_handling.model.AccountBalance;
import com.priitro.bank_account_handling.enums.CurrencyEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
    List<AccountBalance> findAllByAccountNumber(String accountNumber);

    Optional<AccountBalance> findByAccountNumberAndCurrency(String accountNumber, CurrencyEnum currency);
}
