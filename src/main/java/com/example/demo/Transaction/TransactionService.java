package com.example.demo.Transaction;

import com.example.demo.User.User;
import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    
    private Account senderAccount;
    
    private Account recieverAccount;

    @Transactional
    public void sendMoney(Long senderUserId, Long recipientUserId, BigDecimal transactionAmount) {
        try {
            
            Optional <Account> optionalSenderAccount = accountRepository.findById(senderUserId);

            Optional <Account> optionalRecieverAccount = accountRepository.findById(recipientUserId); // Validation, deduction, addition, and recording steps using BigDecimal

            optionalSenderAccount.ifPresent(account -> senderAccount = account);

            optionalRecieverAccount.ifPresent(account -> recieverAccount = account);
            

            if (senderAccount.getBalance().compareTo(transactionAmount) < 0) {
                System.out.println("Insufficient funds, please choose a lower amoun");
                return;
            }
                senderAccount.setBalance(senderAccount.getBalance().subtract(transactionAmount));
                accountRepository.save(senderAccount); // Update the sender's account

                recieverAccount.setBalance(recieverAccount.getBalance().add(transactionAmount));
                accountRepository.save(recieverAccount);

                Transaction transaction = new Transaction();

                transaction.setSenderUserId(senderAccount.getUserId());
                transaction.setRecipientUserId(recieverAccount.getUserId());
                transaction.setAmount(transactionAmount);
                transaction.setTimestamp(LocalDateTime.now());

                transactionRepository.save(transaction);

            } catch (Exception e) {
            // Handle exceptions and roll back the transaction if needed

            System.out.println("Transaction failed due to an unexpected error");
        }
    }

}
