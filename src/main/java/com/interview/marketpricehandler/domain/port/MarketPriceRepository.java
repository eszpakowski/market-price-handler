package com.interview.marketpricehandler.domain.port;

import com.interview.marketpricehandler.domain.model.MarketPrice;

public interface MarketPriceRepository {
    void save(MarketPrice price);

    MarketPrice findByInstrumentId(String instrumentName);
}
