package com.example.demo.account;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import com.example.demo.User.User;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;
    String username;

    //consider for more money operations import org.javamoney.moneta.Money;
    BigDecimal balance = BigDecimal.ZERO;
    String currency = "KES";

        
        @OneToOne
        //@MapsId
        @JoinColumn(name="userId", nullable = false, unique = true)
        User user;

    public Account(Long accountId, String username, BigDecimal balance, String currency) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
    }
    public Account() {
        this.currency = "KES";

    }

}
