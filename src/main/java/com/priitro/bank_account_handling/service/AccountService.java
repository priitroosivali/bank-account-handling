package com.priitro.bank_account_handling.service;

import com.priitro.bank_account_handling.dto.CreateAccountRequestDTO;
import com.priitro.bank_account_handling.model.Account;
import com.priitro.bank_account_handling.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    private static final String externalURL = "https://httpstat.us/200";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountBalanceService accountBalanceService;

    public ResponseEntity<String> externalSystemCall() {
        RestTemplate restTemplate = new RestTemplate();
        String body = restTemplate.getForObject(externalURL, String.class);
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<String> createAccount(CreateAccountRequestDTO request) {
        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        try {
            accountBalanceService.createAccountBalances(accountRepository.save(account));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Account created failed.");
        }
        return ResponseEntity.ok("Account created successfully.");
    }
}
