package com.example.mintos.model.exchange;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExchangeRateTest {

    @Test
    void testExchangeRateFields() {
        // Given
        Long id = 1L;
        BigDecimal rate = new BigDecimal("1.25");
        String currencyCode = "USD";
        LocalDateTime lastModified = LocalDateTime.now();

        // When
        ExchangeRate exchangeRate = new ExchangeRate(id, rate, currencyCode, lastModified);

        // Then
        assertNotNull(exchangeRate);
        assertEquals(id, exchangeRate.getId());
        assertEquals(rate, exchangeRate.getRate());
        assertEquals(currencyCode, exchangeRate.getCurrencyCode());
        assertEquals(lastModified, exchangeRate.getLastModified());
    }
}