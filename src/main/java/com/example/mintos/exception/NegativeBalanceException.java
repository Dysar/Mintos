package com.example.mintos.exception;

public class NegativeBalanceException extends Exception {
    public NegativeBalanceException(String message) {
        super(message);
    }
}
