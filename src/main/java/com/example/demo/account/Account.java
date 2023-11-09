package com.example.demo.account;

import lombok.Data;

@Data
public class Account {

    Long accountId;

    Long userId;
    Integer balance;
    String currency;

    public Account(Long accountId, Long userId, Integer balance, String currency) {

        this.userId = userId;
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
    }
    public Account() {
    }

}
