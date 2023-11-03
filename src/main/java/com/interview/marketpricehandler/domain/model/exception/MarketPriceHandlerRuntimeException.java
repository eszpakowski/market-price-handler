package com.interview.marketpricehandler.domain.model.exception;

public class MarketPriceHandlerRuntimeException extends RuntimeException {
    public MarketPriceHandlerRuntimeException(String message) {
        super(message);
    }

    public MarketPriceHandlerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
