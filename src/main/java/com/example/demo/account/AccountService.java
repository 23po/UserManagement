package com.example.demo.account;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.User.User;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(User user) {

        if (user == null ) {
            throw new IllegalArgumentException("user mus be provided");
        }

        Account newAccount = new Account();

        System.out.println(user);

        newAccount.setUsername(user.getUsername());
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setUser(user);

//newAccount.setUser(request.);

        return accountRepository.save(newAccount);
    }



}
