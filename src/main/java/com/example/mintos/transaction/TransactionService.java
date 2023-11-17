package com.example.mintos.transaction;

import com.example.mintos.account.Account;
import com.example.mintos.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;
    public List<Transaction> findBySourceAccountIDOrDestinationAccountIDEquals(Long accountID) {
        return repository.findBySourceAccountIDOrDestinationAccountIDEquals(accountID, accountID);
    }

    public Transaction createTransaction(
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount,
            String currencyCode) {
        Transaction newTransaction = new Transaction();
        newTransaction.setSourceAccountID(sourceAccountId);
        newTransaction.setDestinationAccountID(destinationAccountId);
        newTransaction.setTimestamp(LocalDateTime.from(Instant.now()));
        newTransaction.setCurrencyCode(currencyCode);
        newTransaction.setAmount(amount);
        // Set other fields as needed

        return repository.save(newTransaction);
    }
}

