package com.example.mintos.service.exchange;

import com.example.mintos.service.exchange.ExchangeRateServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangeRateServiceTest {

//    @Test
//    public void testGetExchangeRate_SuccessfulResponse() {
//        // Arrange
//        RestTemplate restTemplateMock = mock(RestTemplate.class);
//
//        // Mocking the response
//        Map<String, Object> responseBody = new HashMap<>();
//        Map<String, Object> ratesMap = new HashMap<>();
//        ratesMap.put("GBP", 0.72007);
//        responseBody.put("rates", ratesMap);
//
//        ResponseEntity<Map> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
//
//        // Mocking the RestTemplate behavior
//        when(restTemplateMock.getForEntity("https://example.com/api?base=EUR&symbols=GBP&access_key=your-api-key", Map.class))
//                .thenReturn(responseEntity);
//
//        // Creating the service with constructor injection
//        ExchangeRateService exchangeRateService = new ExchangeRateService("your-api-key", "https://example.com/api", restTemplateMock);
//
//        // Act
//        BigDecimal exchangeRate = exchangeRateService.getExchangeRate("GBP");
//
//        // Assert
//        assertEquals(new BigDecimal("0.72007"), exchangeRate);
//    }
}