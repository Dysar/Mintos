package com.example.mintos.model.account;

import com.example.mintos.dto.model.account.AccountDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountTest {

    @Test
    void testConvertEntityToDTO() {
        // Given
        Account account = new Account(1L, 123L, new BigDecimal("1000.50"), "USD");

        // When
        AccountDTO accountDTO = account.convertEntityToDTO();

        // Then
        assertNotNull(accountDTO);
        assertEquals(account.getId(), accountDTO.getId());
        assertEquals(account.getClientID(), accountDTO.getClientID());
        assertEquals(account.getBalance(), accountDTO.getBalance());
        assertEquals(account.getCurrencyCode(), accountDTO.getCurrencyCode());
    }
}