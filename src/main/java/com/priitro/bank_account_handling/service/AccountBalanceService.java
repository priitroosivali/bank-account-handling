package com.priitro.bank_account_handling.service;

import com.priitro.bank_account_handling.dto.AddMoneyToAccountBalanceRequestDTO;
import com.priitro.bank_account_handling.dto.DebitMoneyFromAccountBalanceRequestDTO;
import com.priitro.bank_account_handling.dto.ExchangeCurrencyRequestDTO;
import com.priitro.bank_account_handling.dto.GetAccountBalanceResponseDTO;
import com.priitro.bank_account_handling.model.Account;
import com.priitro.bank_account_handling.model.AccountBalance;
import com.priitro.bank_account_handling.enums.CurrencyEnum;
import com.priitro.bank_account_handling.mapper.AccountBalanceMapper;
import com.priitro.bank_account_handling.repository.AccountBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private AccountBalanceMapper accountBalanceMapper;

    public void createAccountBalances(Account account) {
        List<AccountBalance> accountBalances = new ArrayList<>();
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            AccountBalance accountBalance = new AccountBalance();
            accountBalance.setAccountNumber(account.getAccountNumber());
            accountBalance.setCurrency(currency);
            accountBalance.setAmountInCents(0L);
            accountBalances.add(accountBalance);
        }
        accountBalanceRepository.saveAll(accountBalances);
    }

    public ResponseEntity<GetAccountBalanceResponseDTO> getAccountBalances(String accountNumber) {
        return ResponseEntity.ok(accountBalanceMapper.toDto(accountBalanceRepository.findAllByAccountNumber(accountNumber)));
    }

    public ResponseEntity<String> addMoneyToAccountBalance(AddMoneyToAccountBalanceRequestDTO request) {
        Optional<AccountBalance> accountBalance = accountBalanceRepository.findByAccountNumberAndCurrency(request.getAccountNumber(), request.getCurrency());
        if(accountBalance.isPresent()) {
            accountBalance.get().setAmountInCents(accountBalance.get().getAmountInCents() + request.getAmountInCents());
            accountBalanceRepository.save(accountBalance.get());
            return ResponseEntity.ok("Added funds successfully.");
        }
        return ResponseEntity.badRequest().body("Such a currency is not supported or used account number doesn't exist.");
    }

    public ResponseEntity<String> debitMoneyFromAccountBalance(DebitMoneyFromAccountBalanceRequestDTO request) {
        Optional<AccountBalance> accountBalance = accountBalanceRepository.findByAccountNumberAndCurrency(request.getAccountNumber(), request.getCurrency());
        if(accountBalance.isPresent()) {
            accountBalance.get().setAmountInCents(accountBalance.get().getAmountInCents() - request.getAmountInCents());
            accountBalanceRepository.save(accountBalance.get());
            return ResponseEntity.ok("Debited funds successfully!");
        }
        return ResponseEntity.badRequest().body("Such a currency is not supported or used account number doesn't exist.");
    }

    public ResponseEntity<String> exchangeCurrency(ExchangeCurrencyRequestDTO request) {
        Optional<AccountBalance> accountBalanceFrom = accountBalanceRepository.findByAccountNumberAndCurrency(request.getAccountNumber(), request.getCurrencyFrom());
        Optional<AccountBalance> accountBalanceTo = accountBalanceRepository.findByAccountNumberAndCurrency(request.getAccountNumber(), request.getCurrencyTo());
        if(accountBalanceFrom.isPresent() && accountBalanceTo.isPresent()) {
            if(accountBalanceFrom.get().getAmountInCents() - request.getAmountInCents() < 0) {
                return ResponseEntity.badRequest().body("There are not enough funds available for selected currency. Exchange didn't go through!");
            }
            accountBalanceFrom.get().setAmountInCents(accountBalanceFrom.get().getAmountInCents() - request.getAmountInCents());
            accountBalanceTo.get().setAmountInCents(accountBalanceTo.get().getAmountInCents() + convertToCurrency(request.getCurrencyFrom(), request.getCurrencyTo(), request.getAmountInCents()));
            accountBalanceRepository.save(accountBalanceFrom.get());
            accountBalanceRepository.save(accountBalanceTo.get());
            return ResponseEntity.ok("Exchanged currencies successfully!");
        }
        return ResponseEntity.badRequest().body("On of the currencies is not supported or used account number doesn't exist.");
    }

    private Long convertToCurrency(CurrencyEnum currencyFrom, CurrencyEnum currencyTo, Long amountInCents) {
        long amountInCentsUsd = (long) (amountInCents * currencyFrom.getConversionRateToUsd());
        return (long) (amountInCentsUsd / currencyTo.getConversionRateToUsd());
    }

}
