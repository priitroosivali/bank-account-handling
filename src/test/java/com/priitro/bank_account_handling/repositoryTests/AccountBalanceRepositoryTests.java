package com.priitro.bank_account_handling.repositoryTests;

import com.priitro.bank_account_handling.enums.CurrencyEnum;
import com.priitro.bank_account_handling.model.Account;
import com.priitro.bank_account_handling.model.AccountBalance;
import com.priitro.bank_account_handling.repository.AccountBalanceRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AccountBalanceRepositoryTests {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Test
    @Transactional
    @Rollback
    public void testCreateAccount() {
        AccountBalance accountBalance = AccountBalance.builder()
                .accountNumber("TEST_00")
                .amountInCents(100L)
                .currency(CurrencyEnum.EUR)
                .build();

        AccountBalance savedBalance = accountBalanceRepository.save(accountBalance);

        assertNotNull(savedBalance);
        assertNotNull(savedBalance.getId());
        assertEquals(savedBalance.getAccountNumber(), accountBalance.getAccountNumber());
        assertEquals(savedBalance.getAmountInCents(), accountBalance.getAmountInCents());
        assertEquals(savedBalance.getCurrency(), accountBalance.getCurrency());
    }
}
