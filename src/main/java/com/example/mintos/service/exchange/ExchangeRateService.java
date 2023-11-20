package com.example.mintos.service.exchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class ExchangeRateService {

    private final String apiKey;
    private final String apiUrl;
   // private final RestTemplate restTemplate;

    public ExchangeRateService(
            @Value("${api.key}") String apiKey,
            @Value("${api.url}") String apiUrl
          //  RestTemplate restTemplate
    ) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        //this.restTemplate = restTemplate;
    }

    public BigDecimal getExchangeRate(String targetCurrency) {
        // Build the API URL with the necessary query parameters
        ResponseEntity<Map> response = makeApiCall(targetCurrency);

        // Check if the request was successful
        if (response.getStatusCode().is2xxSuccessful()) {
            // Parse the response and extract the exchange rate
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("rates")) {
                Map<String, Object> ratesMap = (Map<String, Object>) responseBody.get("rates");

                // Check if the ratesMap has the "GBP" field
                if (ratesMap != null && ratesMap.containsKey(targetCurrency)) {
                    return new BigDecimal((Double) ratesMap.get(targetCurrency));
                }
            }
        }

        // If the request fails or the exchange rate is not found, return null
        return null;
    }

    private ResponseEntity<Map> makeApiCall(String targetCurrency) {
        String url = String.format("%s?base=EUR&symbols=%s&access_key=%s", apiUrl, targetCurrency, apiKey);

        System.out.println(url);
        // Create a RetryTemplate for retrying the HTTP request
        RetryTemplate retryTemplate = new RetryTemplate();

        // Make the HTTP request with a timeout of 5 seconds
        ResponseEntity<Map> response = retryTemplate.execute(context -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("Accept", "application/json");
                return execution.execute(request, body);
            });

            return restTemplate.getForEntity(url, Map.class);
        });
        return response;
    }
}
