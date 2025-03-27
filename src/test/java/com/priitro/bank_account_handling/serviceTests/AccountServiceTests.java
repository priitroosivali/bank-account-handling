package com.priitro.bank_account_handling.serviceTests;

import com.priitro.bank_account_handling.dto.CreateAccountRequestDTO;
import com.priitro.bank_account_handling.model.Account;
import com.priitro.bank_account_handling.repository.AccountRepository;
import com.priitro.bank_account_handling.service.AccountBalanceService;
import com.priitro.bank_account_handling.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountBalanceService accountBalanceService;
    @Mock
    private AccountRepository accountRepository;

    private CreateAccountRequestDTO createAccountRequestDTO;

    @BeforeEach
    void setUp() {
        createAccountRequestDTO = CreateAccountRequestDTO.builder()
                .accountNumber("TEST_ACC_NUMBER")
                .build();
    }

    @Test
    public void createAccountSuccess() {
        when(accountRepository.save(any())).thenReturn(null);
        doNothing().when(accountBalanceService).createAccountBalances(any());
        ResponseEntity<String> response = accountService.createAccount(createAccountRequestDTO);
        verify(accountRepository, times(1)).save(any());
        verify(accountBalanceService, times(1)).createAccountBalances(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account created successfully.", response.getBody());
    }

    @Test
    public void createAccountUnsuccessful() {
        when(accountRepository.save(any())).thenThrow();
        ResponseEntity<String> response = accountService.createAccount(createAccountRequestDTO);
        verify(accountRepository, times(1)).save(any());
        verify(accountBalanceService, times(0)).createAccountBalances(any());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Account created failed.", response.getBody());
    }
}
