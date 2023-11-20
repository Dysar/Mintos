package com.example.mintos.exception;
import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyRateNotFoundExceptionTest {

    @Test
    public void testCurrencyRateNotFoundException() {
        String errorMessage = "Currency rate not found.";
        CurrencyRateNotFoundException exception = new CurrencyRateNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test(expected = CurrencyRateNotFoundException.class)
    public void testCurrencyRateNotFoundExceptionWithNoMessage() throws CurrencyRateNotFoundException {
        throw new CurrencyRateNotFoundException("Currency rate not found.");
    }
}