package com.example.mintos.exception;
import org.junit.Test;
import static org.junit.Assert.*;

public class NegativeBalanceExceptionTest {

    @Test
    public void testNegativeBalanceException() {
        String errorMessage = "Negative balance not allowed.";
        NegativeBalanceException exception = new NegativeBalanceException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test(expected = NegativeBalanceException.class)
    public void testNegativeBalanceExceptionWithNoMessage() throws NegativeBalanceException {
        throw new NegativeBalanceException("Negative balance not allowed.");
    }
}
