package com.example.demo.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private Long transactionId;
    private Long senderUserId;
    private Long recipientUserId;

    private BigDecimal transactionAmount;
    private LocalDateTime transactionDateTime;
}
