package com.example.demo.Transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {

    private Long transactionId;
    private Long senderUserId;
    private Long recipientUserId;

    private BigDecimal amount;
    private LocalDateTime timestamp;
}
