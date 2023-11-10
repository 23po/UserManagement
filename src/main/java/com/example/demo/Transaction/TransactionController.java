package com.example.demo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam Long senderUserId,
                                       @RequestParam Long recipientUserId,
                                       @RequestParam BigDecimal amount) {

        try {

            transactionService.sendMoney(senderUserId, recipientUserId, amount);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

