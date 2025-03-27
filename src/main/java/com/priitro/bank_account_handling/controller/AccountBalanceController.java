package com.priitro.bank_account_handling.controller;

import com.priitro.bank_account_handling.dto.AddMoneyToAccountBalanceRequestDTO;
import com.priitro.bank_account_handling.dto.DebitMoneyFromAccountBalanceRequestDTO;
import com.priitro.bank_account_handling.dto.ExchangeCurrencyRequestDTO;
import com.priitro.bank_account_handling.dto.GetAccountBalanceResponseDTO;
import com.priitro.bank_account_handling.service.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
public class AccountBalanceController {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<GetAccountBalanceResponseDTO> getAccountBalance(@PathVariable String accountNumber) {
        return accountBalanceService.getAccountBalances(accountNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMoneyToAccountBalance(@RequestBody AddMoneyToAccountBalanceRequestDTO request) {
        return accountBalanceService.addMoneyToAccountBalance(request);
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debitMoneyFromAccountBalance(@RequestBody DebitMoneyFromAccountBalanceRequestDTO request) {
        return accountBalanceService.debitMoneyFromAccountBalance(request);
    }

    @PostMapping("/exchange")
    public ResponseEntity<String> exchangeCurrency(@RequestBody ExchangeCurrencyRequestDTO request) {
        return accountBalanceService.exchangeCurrency(request);
    }


}
