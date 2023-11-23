package com.example.mintos.controller.v1.transaction;

import com.example.mintos.dto.model.transaction.TransactionDTO;
import com.example.mintos.dto.response.Response;
import com.example.mintos.exception.CurrencyRateNotFoundException;
import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.service.transaction.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class TransactionControllerTest {
    @Mock
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransactionSuccess() {
        // Given
        Long sourceAccountId = 1L;
        Long destinationAccountId = 2L;
        BigDecimal amount = BigDecimal.TEN;

        // When
        ResponseEntity<String> response = transactionController.createTransaction(sourceAccountId, destinationAccountId, amount);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals("Transaction created successfully", response.getBody());
        //verify(transactionService, times(1)).createTransaction(sourceAccountId, destinationAccountId, amount);
    }

    @Test
    public void testCreateTransactionFailure() throws AccountNotFoundException, NegativeBalanceException, CurrencyRateNotFoundException {
        // Given
        Long sourceAccountId = 1L;
        Long destinationAccountId = 2L;
        BigDecimal amount = BigDecimal.TEN;
        doThrow(new AccountNotFoundException("Account not found")).when(transactionService)
                .createTransaction(sourceAccountId, destinationAccountId, amount);

        // When
        ResponseEntity<String> response = transactionController.createTransaction(sourceAccountId, destinationAccountId, amount);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("Account not found", response.getBody());
        //verify(transactionService, times(1)).createTransaction(sourceAccountId, destinationAccountId, amount);
    }

    @Test
    public void testGetTransactionHistory() {
        // Given
        Long accountID = 1L;
        int offset = 0;
        int limit = 10;

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        Page<Transaction> transactionPage = new PageImpl<>(transactions);

        when(transactionService.findBySourceAccountIDOrDestinationAccountIDEquals(accountID, PageRequest.of(offset / limit, limit)))
                .thenReturn(transactionPage);

        // When
        ResponseEntity<Response<List<TransactionDTO>>> response = transactionController.getTransactionHistory(accountID, offset, limit);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        //Assertions.assertEquals(transactions.size(), response.getBody().getData().size());
        verify(transactionService, times(1)).findBySourceAccountIDOrDestinationAccountIDEquals(accountID, PageRequest.of(offset / limit, limit));
    }

    @Test
    public void testGetTransactionHistoryException() {
        // Given
        Long accountID = 1L;
        int offset = 0;
        int limit = 10;

        doThrow(new RuntimeException("Internal Server Error")).when(transactionService)
                .findBySourceAccountIDOrDestinationAccountIDEquals(accountID, PageRequest.of(offset / limit, limit));

        // When
        ResponseEntity<Response<List<TransactionDTO>>> response = transactionController.getTransactionHistory(accountID, offset, limit);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(500, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody().getErrors());
        verify(transactionService, times(1)).findBySourceAccountIDOrDestinationAccountIDEquals(accountID, PageRequest.of(offset / limit, limit));
    }
}
