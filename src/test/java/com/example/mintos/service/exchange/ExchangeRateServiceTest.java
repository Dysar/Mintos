package com.example.mintos.service.exchange;

import com.example.mintos.model.exchange.ExchangeRate;
import com.example.mintos.repository.exchange.ExchangeRateRepository;
import com.example.mintos.service.exchange.impl.ExchangeRateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ExchangeRateServiceTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @BeforeEach
    void setUp() throws NoSuchFieldException {
        MockitoAnnotations.openMocks(this);
        Field fieldApiKey = ExchangeRateServiceImpl.class.getDeclaredField("apiKey");
        ReflectionUtils.makeAccessible(fieldApiKey);
        ReflectionUtils.setField(fieldApiKey, exchangeRateService, "123");

        Field fieldApiUrl = ExchangeRateServiceImpl.class.getDeclaredField("apiUrl");
        ReflectionUtils.makeAccessible(fieldApiUrl);
        ReflectionUtils.setField(fieldApiUrl, exchangeRateService, "123");
    }

    @Test
    void getExchangeRate_ReturnsExchangeRate_WhenCurrencyFoundInRepository() {
        // Arrange
        String targetCurrency = "USD";
        BigDecimal expectedRate = BigDecimal.valueOf(1.25);

        when(exchangeRateRepository.findByCurrencyCode(targetCurrency))
                .thenReturn(Optional.of(new ExchangeRate(1L, expectedRate,targetCurrency, LocalDateTime.now())));

        // Act
        BigDecimal actualRate = exchangeRateService.getExchangeRate(targetCurrency);

        // Assert
        assertEquals(expectedRate, actualRate);
    }

    @Test
    void getExchangeRate_ReturnsNull_WhenCurrencyNotFoundInRepository() {
        // Arrange
        String targetCurrency = "GBP";

        when(exchangeRateRepository.findByCurrencyCode(targetCurrency))
                .thenReturn(Optional.empty());

        // Act
        BigDecimal actualRate = exchangeRateService.getExchangeRate(targetCurrency);

        // Assert
        assertEquals(null, actualRate);
    }

    // Additional tests for error scenarios, internet connection issues, etc.

}
