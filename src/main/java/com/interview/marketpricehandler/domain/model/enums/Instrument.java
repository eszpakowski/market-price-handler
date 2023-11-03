package com.interview.marketpricehandler.domain.model.enums;

import com.interview.marketpricehandler.domain.model.exception.MarketPriceHandlerRuntimeException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Instrument {
    EUR_USD("EUR/USD"),
    GPB_USD("GPB/USD"),
    EUR_JPY("EUR/JPY");

    private final String name;

    public static Instrument fromString(String instrumentName) {
        for (Instrument instrument : Instrument.values()) {
            if (instrument.getName().equalsIgnoreCase(instrumentName)) {
                return instrument;
            }
        }
        throw new MarketPriceHandlerRuntimeException(
                String.format("Unknown instrumentName! [name={%s}]", instrumentName));
    }

    public String getName() {
        return name;
    }
}
