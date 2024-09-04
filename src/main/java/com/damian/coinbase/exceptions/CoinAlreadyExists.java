package com.damian.coinbase.exceptions;

public class CoinAlreadyExists extends RuntimeException {
    public CoinAlreadyExists(String message) {
        super(message);
    }
}
