package com.example.mintos.dto.transaction;
import com.example.mintos.dto.model.transaction.TransactionDTO;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionDTOTest {

    @Test
    public void testGetterSetter() {
        // Create an instance of TransactionDTO
        TransactionDTO transactionDTO = new TransactionDTO();

        // Set values using setters
        transactionDTO.setId(1L);
        transactionDTO.setSourceAccountID(2L);
        transactionDTO.setDestinationAccountID(3L);
        transactionDTO.setTimestamp(LocalDateTime.now());
        transactionDTO.setCurrencyCode("USD");
        transactionDTO.setAmount(BigDecimal.valueOf(50.00));

        // Verify values using getters
        assertEquals(1L, transactionDTO.getId());
        assertEquals(2L, transactionDTO.getSourceAccountID());
        assertEquals(3L, transactionDTO.getDestinationAccountID());
        assertNotNull(transactionDTO.getTimestamp());
        assertEquals("USD", transactionDTO.getCurrencyCode());
        assertEquals(BigDecimal.valueOf(50.00), transactionDTO.getAmount());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create an instance of TransactionDTO using the no-args constructor
        TransactionDTO transactionDTO = new TransactionDTO();

        // Verify that the instance is not null
        assertNotNull(transactionDTO);

        // Verify that the default values are set (assuming default values are meaningful)
        assertNull(transactionDTO.getId());
        assertNull(transactionDTO.getSourceAccountID());
        assertNull(transactionDTO.getDestinationAccountID());
        assertNull(transactionDTO.getTimestamp());
        assertNull(transactionDTO.getCurrencyCode());
        assertNull(transactionDTO.getAmount());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create an instance of TransactionDTO using the all-args constructor
        TransactionDTO transactionDTO = new TransactionDTO(1L, 2L, 3L, LocalDateTime.now(), "USD", BigDecimal.valueOf(50.00));

        // Verify that the instance is not null
        assertNotNull(transactionDTO);

        // Verify that values are set correctly
        assertEquals(1L, transactionDTO.getId());
        assertEquals(2L, transactionDTO.getSourceAccountID());
        assertEquals(3L, transactionDTO.getDestinationAccountID());
        assertNotNull(transactionDTO.getTimestamp());
        assertEquals("USD", transactionDTO.getCurrencyCode());
        assertEquals(BigDecimal.valueOf(50.00), transactionDTO.getAmount());
    }

    // Add more test cases as needed
}
