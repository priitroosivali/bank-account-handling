package com.priitro.bank_account_handling.repositoryTests;

import com.priitro.bank_account_handling.model.Account;
import com.priitro.bank_account_handling.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    @Rollback
    public void testCreateAccount() {
        Account account = Account.builder()
                .accountNumber("TEST_00")
                .build();

        Account savedAccount = accountRepository.save(account);

        assertNotNull(savedAccount);
        assertNotNull(savedAccount.getId());
        assertEquals(savedAccount.getAccountNumber(), account.getAccountNumber());
    }
}
