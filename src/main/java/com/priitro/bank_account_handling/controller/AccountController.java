package com.priitro.bank_account_handling.controller;

import com.priitro.bank_account_handling.dto.CreateAccountRequestDTO;
import com.priitro.bank_account_handling.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<String> externalSystemCall() {
        return accountService.externalSystemCall();
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequestDTO account) {
        return accountService.createAccount(account);
    }
}
