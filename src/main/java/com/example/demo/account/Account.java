package com.example.demo.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;
    Long userId;
    String username;
    BigDecimal balance = BigDecimal.ZERO;
    String currency;

    public Account(Long accountId, Long userId, String username, BigDecimal balance, String currency) {

        this.userId = userId;
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
    }
    public Account() {
    }

}
