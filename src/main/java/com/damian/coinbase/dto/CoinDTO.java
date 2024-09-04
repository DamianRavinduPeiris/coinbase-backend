package com.damian.coinbase.dto;

import java.io.Serializable;

public record CoinDTO(String coinId,String name, String  symbol, double price) implements Serializable {}
