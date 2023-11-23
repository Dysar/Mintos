package com.example.mintos.service.transaction;
import com.example.mintos.exception.CurrencyRateNotFoundException;
import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public interface TransactionService {

    /**
     * Retrieves a page of transactions based on source or destination account ID.
     *
     * @param accountID The source or destination account ID
     * @param pageable  The pageable information for pagination
     * @return Page of transactions matching the criteria
     */
    Page<Transaction> findBySourceAccountIDOrDestinationAccountIDEquals(Long accountID, Pageable pageable);

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
    Transaction createTransaction(
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount
    ) throws AccountNotFoundException, NegativeBalanceException, CurrencyRateNotFoundException;
}

