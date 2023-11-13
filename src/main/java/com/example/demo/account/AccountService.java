package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Request request) {

        Account newAccount = new Account();

        newAccount.setUsername(request.username);

        return accountRepository.save(newAccount);
    }


}
