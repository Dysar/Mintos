package com.example.mintos.repository.exchange;

import com.example.mintos.model.exchange.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link ExchangeRate} entities in the database.
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    /**
     * Retrieves an optional exchange rate based on the currency code.
     *
     * @param currencyCode The currency code for which to retrieve the exchange rate
     * @return Optional containing the exchange rate if found, otherwise empty
     */
    Optional<ExchangeRate> findByCurrencyCode(String currencyCode);
}
