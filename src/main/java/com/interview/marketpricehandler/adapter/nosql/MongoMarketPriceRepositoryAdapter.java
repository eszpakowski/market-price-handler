package com.interview.marketpricehandler.adapter.nosql;

import com.interview.marketpricehandler.adapter.nosql.document.MarketPriceDocument;
import com.interview.marketpricehandler.domain.model.enums.Instrument;
import com.interview.marketpricehandler.domain.port.MarketPriceRepository;
import com.interview.marketpricehandler.domain.model.MarketPrice;
import com.interview.marketpricehandler.domain.model.exception.MarketPriceHandlerRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoMarketPriceRepositoryAdapter implements MarketPriceRepository {
    private final MongoMarketPriceRepository mongoMarketPriceRepository;

    @Override
    public void save(MarketPrice price) {
        MarketPriceDocument toSave = MarketPriceDocument.builder()
                .instrumentName(price.instrument().getName())
                .bid(price.bid())
                .ask(price.ask())
                .timestamp(price.timestamp())
                .build();

        mongoMarketPriceRepository.save(toSave);
    }

    @Override
    public MarketPrice findByInstrumentId(String instrumentName) {
        MarketPriceDocument result = mongoMarketPriceRepository.findByInstrumentName(instrumentName)
                .orElseThrow(() -> new MarketPriceHandlerRuntimeException(
                        String.format("Market price not found! [instrumentName={%s}]", instrumentName)));

        return new MarketPrice(
                Instrument.fromString(result.getInstrumentName()),
                result.getBid(),
                result.getAsk(),
                result.getTimestamp()
        );
    }
}
