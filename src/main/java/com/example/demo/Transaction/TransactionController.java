package com.example.demo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Transaction> send(@RequestBody Transaction request) {

        try {

           Transaction response = transactionService.sendMoney(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/gift/{userId}")
    public ResponseEntity<Transaction> giftMe (@PathVariable Long userId) {

        return ResponseEntity.ok().body(transactionService.giftMoney(userId));

    } 


    /**
     * @return TransactionService return the transactionService
     */
    public TransactionService getTransactionService() {
        return transactionService;
    }

    /**
     * @param transactionService the transactionService to set
     */
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

}

