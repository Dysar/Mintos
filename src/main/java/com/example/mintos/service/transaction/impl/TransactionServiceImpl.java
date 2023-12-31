package com.example.mintos.service.transaction.impl;

import com.example.mintos.exception.CurrencyRateNotFoundException;
import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.model.account.Account;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.repository.transaction.TransactionRepository;
import com.example.mintos.service.exchange.ExchangeRateService;
import com.example.mintos.service.exchange.impl.ExchangeRateServiceImpl;
import com.example.mintos.service.transaction.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            ExchangeRateService exchangeRateService) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * Retrieves a page of transactions based on source or destination account ID.
     *
     * @param accountID The source or destination account ID
     * @param pageable  The pageable information for pagination
     * @return Page of transactions matching the criteria
     */
    public Page<Transaction> findBySourceAccountIDOrDestinationAccountIDEquals(Long accountID, Pageable pageable) {
        return transactionRepository.findBySourceAccountIDOrDestinationAccountIDEqualsOrderByTimestampDesc(accountID, accountID, pageable);
    }

    /**
     * Creates a new transaction, updating source and destination account balances.
     *
     * @param sourceAccountId      The ID of the source account
     * @param destinationAccountId The ID of the destination account
     * @param amount               The amount to transfer (in source account currency)
     * @return The created transaction
     * @throws AccountNotFoundException      If source or destination account is not found
     * @throws NegativeBalanceException        If the source account has insufficient balance
     * @throws CurrencyRateNotFoundException  If the currency rate for the transaction is not found
     */
    @Transactional
    public Transaction createTransaction(
            Long sourceAccountId,
            Long destinationAccountId,
            // the amount is presented in the source account currency
            BigDecimal amount
    ) throws AccountNotFoundException, NegativeBalanceException, CurrencyRateNotFoundException {


        Optional<Account> optionalSourceAccount = accountRepository.findById(sourceAccountId);
        Account sourceAccount = optionalSourceAccount.orElseThrow(() ->
                new AccountNotFoundException("Source account not found for ID: " + sourceAccountId));

        Optional<Account> optionalDestinationAccount = accountRepository.findById(destinationAccountId);
        Account destAccount = optionalDestinationAccount.orElseThrow(() ->
                new AccountNotFoundException("Destination account not found for ID: " + destinationAccountId));


        if (sourceAccount.getBalance().subtract(amount).signum() < 0) {
            throw new NegativeBalanceException("Balance cannot be less than zero.");
        }

        // get the exchange rate and convert the currency
        BigDecimal rate = exchangeRateService.getExchangeRate(destAccount.getCurrencyCode());
        if (rate == null) {
            throw new CurrencyRateNotFoundException("Could not fetch the currency rate for the transaction.");
        }
        System.out.println(rate);

        Transaction newTransaction = new Transaction();
        newTransaction.setSourceAccountID(sourceAccountId);
        newTransaction.setDestinationAccountID(destinationAccountId);
        newTransaction.setTimestamp(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
        // set the currency to the receiver's currency
        newTransaction.setCurrencyCode(destAccount.getCurrencyCode());
        // multiply the source account's amount by the current rate
        newTransaction.setAmount(amount.multiply(rate));


        //update the source account's balance, update the dest account's balance
        accountRepository.decreaseBalance(sourceAccountId, amount);
        accountRepository.increaseBalance(destinationAccountId, amount.multiply(rate));

        return transactionRepository.save(newTransaction);
    }
}

