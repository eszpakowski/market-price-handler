package com.interview.marketpricehandler.domain.model;

import com.interview.marketpricehandler.domain.model.enums.Instrument;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record MarketPrice(Instrument instrument, BigDecimal bid, BigDecimal ask, LocalDateTime timestamp) {
    public MarketPrice {
        Objects.requireNonNull(instrument);
        Objects.requireNonNull(bid);
        Objects.requireNonNull(ask);
        Objects.requireNonNull(timestamp);

        if (bid.compareTo(ask) >= 0) {
            throw new IllegalArgumentException(
                    String.format("Bid value is larger or equal to ask value! [bid=%f, ask=%f]",
                            bid.doubleValue(), ask.doubleValue()));
        }
    }
}


