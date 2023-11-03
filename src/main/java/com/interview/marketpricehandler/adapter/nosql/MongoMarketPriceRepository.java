package com.interview.marketpricehandler.adapter.nosql;

import com.interview.marketpricehandler.adapter.nosql.document.MarketPriceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoMarketPriceRepository extends MongoRepository<MarketPriceDocument, String> {
    Optional<MarketPriceDocument> findByInstrumentName(String instrumentName);
}
