package com.priitro.bank_account_handling.utils;

import com.priitro.bank_account_handling.dto.CreateAccountRequestDTO;
import com.priitro.bank_account_handling.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private AccountService accountService;

    public void run(ApplicationArguments args) {
        CreateAccountRequestDTO account = new CreateAccountRequestDTO();
        account.setAccountNumber("0000_TEST");
        accountService.createAccount(account);
    }
}
