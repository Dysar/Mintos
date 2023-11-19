package com.example.mintos.service.transaction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRateService {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.url}")
    private String apiUrl;

    public BigDecimal getExchangeRate(String baseCurrency, String targetCurrency) {
        // Build the API URL with the necessary query parameters
        String url = String.format("%s?from=%s&to=%s&apiKey=%s", apiUrl, baseCurrency,targetCurrency, apiKey);

        // Create a RetryTemplate for retrying the HTTP request
        RetryTemplate retryTemplate = new RetryTemplate();

        // Make the HTTP request with a timeout of 5 seconds
        ResponseEntity<Map> response = retryTemplate.execute(context -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("Accept", "application/json");
                return execution.execute(request, body);
            });

            return restTemplate.getForEntity(apiUrl, Map.class);
        });

        // Check if the request was successful
        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse the response and extract the exchange rate
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("result")) {
                return (BigDecimal) responseBody.get("result");
            }
        }

        // If the request fails or the exchange rate is not found, return null or handle accordingly
        return null;
    }
}
