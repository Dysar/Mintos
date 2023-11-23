package com.example.mintos.service.exchange;
import java.math.BigDecimal;

public interface ExchangeRateService {

    /**
     * Retrieves the exchange rate for the specified target currency.
     *
     * @param targetCurrency The target currency code
     * @return The exchange rate or null if not found
     */
    BigDecimal getExchangeRate(String targetCurrency);
}

