package com.example.mintos.service.exchange.impl;

import com.example.mintos.model.exchange.ExchangeRate;
import com.example.mintos.repository.exchange.ExchangeRateRepository;
import com.example.mintos.service.exchange.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final String apiKey;
    private final String apiUrl;
   // private final RestTemplate restTemplate;
    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(
            @Value("${api.key}") String apiKey,
            @Value("${api.url}") String apiUrl,
          //  RestTemplate restTemplate
            ExchangeRateRepository exchangeRateRepository
    ) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        //this.restTemplate = restTemplate;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public BigDecimal getExchangeRate(String targetCurrency) {

        // Build the API URL with the necessary query parameters
        ResponseEntity<Map> response = makeApiCall();

        // Check if the request was successful
        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse the response and extract the exchange rate
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("rates")) {
                Map<String, Object> ratesMap = (Map<String, Object>) responseBody.get("rates");

                // Check if the ratesMap has the "GBP" field
                if (ratesMap != null && ratesMap.containsKey(targetCurrency)) {
                    this.persistExchangeRates(ratesMap);
                    return BigDecimal.valueOf((Double) ratesMap.get(targetCurrency));
                }
            }
        } else {
            // it's best to use the newest exchange rate. But if the API is unavailable, use the cached exchange rates from the database
            return getTargetCurrencyRateFromDb(targetCurrency);
        }

        // If the request fails or the exchange rate is not found, return null
        return null;
    }

    private ResponseEntity<Map> makeApiCall() {
        String url = String.format("%s?base=EUR&access_key=%s", apiUrl, apiKey);

        System.out.println(url);

        // Create a RetryTemplate for retrying the HTTP request
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5);
        retryTemplate.setRetryPolicy(retryPolicy);

        // Configure timeouts for the request factory
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000); // 5 seconds connect timeout
        requestFactory.setReadTimeout(5000);    // 5 seconds read timeout

        try {
            // Make the HTTP request
            ResponseEntity<Map> response = retryTemplate.execute(context -> {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setRequestFactory(requestFactory);
                restTemplate.getInterceptors().add((request, body, execution) -> {
                    request.getHeaders().add("Accept", "application/json");
                    return execution.execute(request, body);
                });

                return restTemplate.getForEntity(url, Map.class);
            });

            return response;
        } catch (ResourceAccessException e) {
            // Handle the case where there's no internet connection
            System.err.println("No internet connection: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
    private void persistExchangeRates(Map<String, Object> ratesMap) {

        System.out.println(ratesMap);
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        // Iterate over the ratesMap entries
        for (Map.Entry<String, Object> entry : ratesMap.entrySet()) {
            String currencyCode = entry.getKey();
            BigDecimal rate = null;
            if (entry.getValue() instanceof Double || entry.getValue() instanceof Integer) {
                rate = BigDecimal.valueOf(((Number) entry.getValue()).doubleValue());
            }

            // Create an entity and add it to the list
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setCurrencyCode(currencyCode);
            exchangeRate.setRate(rate);
            exchangeRate.setLastModified(LocalDateTime.now());
            exchangeRates.add(exchangeRate);
        }

        // Save all entities to the repository
        exchangeRateRepository.saveAll(exchangeRates);
    }


    private BigDecimal getTargetCurrencyRateFromDb(String targetCurrency) {
        Optional<ExchangeRate> optionalExchangeRate = exchangeRateRepository.findByCurrencyCode(targetCurrency);

        return optionalExchangeRate.map(ExchangeRate::getRate).orElse(null);
    }
}
