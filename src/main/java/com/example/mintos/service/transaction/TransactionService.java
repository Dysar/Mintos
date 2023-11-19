package com.example.mintos.service.transaction;

import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.model.account.Account;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.repository.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    //TODO: avoid field injection
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;
    public List<Transaction> findBySourceAccountIDOrDestinationAccountIDEquals(Long accountID) {
        return transactionRepository.findBySourceAccountIDOrDestinationAccountIDEquals(accountID, accountID);
    }

    public Transaction createTransaction(
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount
            ) throws AccountNotFoundException, NegativeBalanceException {


        Optional<Account> optionalSourceAccount = accountRepository.findById(sourceAccountId);
        Account sourceAccount = optionalSourceAccount.orElseThrow(() ->
                new AccountNotFoundException("Source account not found for ID: " + sourceAccountId));


        if (sourceAccount.getBalance().subtract(amount).signum() < 0) {
            throw new NegativeBalanceException("Balance cannot be less than zero.");
        }

        //TODO: get the exchange rate and convert the currency


        Transaction newTransaction = new Transaction();
        newTransaction.setSourceAccountID(sourceAccountId);
        newTransaction.setDestinationAccountID(destinationAccountId);
        newTransaction.setTimestamp(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
        // set the currency to the receiver's currency
        newTransaction.setCurrencyCode(sourceAccount.getCurrencyCode());
        newTransaction.setAmount(amount);

        //update the source account's balance, update the dest account's balance
        accountRepository.decreaseBalance(sourceAccountId, amount);
        accountRepository.increaseBalance(destinationAccountId, amount);

        return transactionRepository.save(newTransaction);
    }
}

