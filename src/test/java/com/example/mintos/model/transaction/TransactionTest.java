package com.example.mintos.model.transaction;

import com.example.mintos.dto.model.transaction.TransactionDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @Test
    public void testConvertEntityToDTO() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setSourceAccountID(100L);
        transaction.setDestinationAccountID(200L);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setCurrencyCode("USD");
        transaction.setAmount(new BigDecimal("100.50"));

        // Act
        TransactionDTO transactionDTO = transaction.convertEntityToDTO();

        // Assert
        assertEquals(transaction.getId(), transactionDTO.getId());
        assertEquals(transaction.getSourceAccountID(), transactionDTO.getSourceAccountID());
        assertEquals(transaction.getDestinationAccountID(), transactionDTO.getDestinationAccountID());
        assertEquals(transaction.getTimestamp(), transactionDTO.getTimestamp());
        assertEquals(transaction.getCurrencyCode(), transactionDTO.getCurrencyCode());
        assertEquals(transaction.getAmount(), transactionDTO.getAmount());
    }

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        Long id = 1L;
        Long sourceAccountID = 100L;
        Long destinationAccountID = 200L;
        LocalDateTime timestamp = LocalDateTime.now();
        String currencyCode = "USD";
        BigDecimal amount = new BigDecimal("100.50");

        // Act
        Transaction transaction = new Transaction(id, sourceAccountID, destinationAccountID, timestamp, currencyCode, amount);

        // Assert
        assertEquals(id, transaction.getId());
        assertEquals(sourceAccountID, transaction.getSourceAccountID());
        assertEquals(destinationAccountID, transaction.getDestinationAccountID());
        assertEquals(timestamp, transaction.getTimestamp());
        assertEquals(currencyCode, transaction.getCurrencyCode());
        assertEquals(amount, transaction.getAmount());
    }

    @Test
    public void testToString() {
        Transaction yourObject = new Transaction();
        yourObject.setCurrencyCode("test");

        assertEquals("Transaction(id=null, sourceAccountID=null, destinationAccountID=null, timestamp=null, currencyCode=test, amount=null)", yourObject.toString());
    }
}

