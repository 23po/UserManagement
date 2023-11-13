package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/account/")
public class AccountController {

 @Autowired AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(Request request) {

       Account newAccount = accountService.createAccount(request);

        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

}
