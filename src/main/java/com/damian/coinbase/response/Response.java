package com.damian.coinbase.response;

import java.io.Serializable;

public record Response(int statusCode, String message, Object data) implements Serializable {}
