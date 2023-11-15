package com.example.demo.Transaction;

import com.example.demo.User.User;
import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    
    // private Account senderAccount;
    // private Account recieverAccount;

    // private Transaction response;

    public Transaction loadMoney(Transaction request) {
     
        // user deposit money to their account
        // from mpesa -- a call to mpesa
        // if this func is calling mpesa, it would expect a response with the amount
        // from bank -- a call to a bank
        // from government /pensions/ social welfare funds / maybe another app all together catering for older demogrphic
      
        log.info("recevied request {}", request);
        
        request.getSenderUserId();
        request.getRecipientUserId();
        
       
       Optional <Account> potentialAccount = accountRepository.findById(request.getRecipientUserId()); 

       Account account = potentialAccount.isPresent() ? potentialAccount.get() : null;
 
       //potential null pointer issue can be fixed by not using ternary operator
       account.setBalance(account.getBalance().add(request.getAmount()));

       Transaction response = new Transaction();

       accountRepository.save(account);

       // transaction should only be saved on success
       return transactionRepository.save(request);
   
    }

    public Transaction giftMoney(Long userId) {
     
     Transaction gift = new Transaction();

     Optional <Account> potentialAccount = accountRepository.findById(userId);

     Account account = new Account();

     if (potentialAccount.isPresent()) {
        account = potentialAccount.get();
    }

 //need to understand this BigDecimal bette   
     account.setBalance(account.getBalance().add(BigDecimal.valueOf(500)));

     accountRepository.save(account);

     gift.setRecipientUserId(userId);
     gift.setTimestamp(LocalDateTime.now());

     log.info("new balance {}", account.getBalance());

     //on success
     return transactionRepository.save(gift);

} 

    @Transactional
    public Transaction sendMoney(Transaction request) {
         
        Transaction response = new Transaction();

         Account senderAccount = new Account();
         Account recieverAccount = new Account();

        try {

            log.info("received request {}", request);
            
            Optional <Account> optionalSenderAccount = accountRepository.findById(request.getSenderUserId());

            Optional <Account> optionalRecieverAccount = accountRepository.findById(request.getRecipientUserId()); // Validation, deduction, addition, and recording steps using BigDecimal

        if (optionalSenderAccount.isPresent()) {
            senderAccount = optionalSenderAccount.get();
        } 

        if (optionalRecieverAccount.isPresent()) {
            recieverAccount = optionalRecieverAccount.get();
        }
            
            // optionalSenderAccount.ifPresent(account -> senderAccount = account);

            //optionalRecieverAccount.ifPresent(account -> recieverAccount = account);
            
            log.info("sender Account: {}", senderAccount);

            log.info("receiver Account: {}", recieverAccount);

            if (senderAccount.getBalance().compareTo(request.getAmount()) < 0) {
                log.info("Insufficient funds, please choose a lower amount");
            } else {
                senderAccount.setBalance(senderAccount.getBalance().subtract(request.getAmount()));
                accountRepository.save(senderAccount); // Update the sender's account

                recieverAccount.setBalance(recieverAccount.getBalance().add(request.getAmount()));
                 
                log.info("new balance {}", recieverAccount.getBalance());
                //updated recipieint account 
                Account account = accountRepository.save(recieverAccount);

                log.info("updated Account {}", account);

                Transaction transactionRecord = transactionRepository.save(request);

                //Transaction transaction = new Transaction();

                log.info("saved Transaction {}", transactionRecord);

                response.setSenderUserId(transactionRecord.getSenderUserId());
                response.setRecipientUserId(transactionRecord.getRecipientUserId());
                response.setAmount(transactionRecord.getAmount());
                request.setTimestamp(LocalDateTime.now());

               log.info("response {}", response);
            }  

            } catch (Exception e) {
            // Handle exceptions and roll back the transaction if needed
            log.info("Some exception {}",e);
        }
        
        log.info("new balance {}", recieverAccount.getBalance());
        return response;
    }

}
