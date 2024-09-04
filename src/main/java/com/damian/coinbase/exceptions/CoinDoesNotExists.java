package com.damian.coinbase.exceptions;

public class CoinDoesNotExists extends  RuntimeException{
    public CoinDoesNotExists(String message) {
        super(message);
    }
}
