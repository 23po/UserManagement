package com.example.demo.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    Long accountId;
    Long userId;
    BigDecimal balance;
    String currency;

    public Account(Long accountId, Long userId, BigDecimal balance, String currency) {

        this.userId = userId;
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
    }
    public Account() {
    }

}
