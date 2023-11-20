package com.example.mintos.dto.account;
import com.example.mintos.dto.model.account.AccountDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AccountDTOTest {

    @Test
    public void testGetterSetter() {
        // Create an instance of AccountDTO
        AccountDTO accountDTO = new AccountDTO();

        // Set values using setters
        accountDTO.setId(1L);
        accountDTO.setClientID(2L);
        accountDTO.setBalance(BigDecimal.valueOf(100.00));
        accountDTO.setCurrencyCode("USD");

        // Verify values using getters
        assertEquals(1L, accountDTO.getId());
        assertEquals(2L, accountDTO.getClientID());
        assertEquals(BigDecimal.valueOf(100.00), accountDTO.getBalance());
        assertEquals("USD", accountDTO.getCurrencyCode());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create an instance of AccountDTO using the no-args constructor
        AccountDTO accountDTO = new AccountDTO();

        // Verify that the instance is not null
        assertNotNull(accountDTO);

        // Verify that the default values are set (assuming default values are meaningful)
        assertNull(accountDTO.getId());
        assertNull(accountDTO.getClientID());
        assertNull(accountDTO.getBalance());
        assertNull(accountDTO.getCurrencyCode());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create an instance of AccountDTO using the all-args constructor
        AccountDTO accountDTO = new AccountDTO(1L, 2L, BigDecimal.valueOf(100.00), "USD");

        // Verify that the instance is not null
        assertNotNull(accountDTO);

        // Verify that values are set correctly
        assertEquals(1L, accountDTO.getId());
        assertEquals(2L, accountDTO.getClientID());
        assertEquals(BigDecimal.valueOf(100.00), accountDTO.getBalance());
        assertEquals("USD", accountDTO.getCurrencyCode());
    }

    // Add more test cases as needed
}
