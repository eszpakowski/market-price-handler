package com.interview.marketpricehandler.domain.service;

import com.interview.marketpricehandler.domain.port.MarketPriceRepository;
import com.interview.marketpricehandler.domain.model.MarketPrice;
import com.interview.marketpricehandler.domain.model.enums.Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketPriceService {
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final BigDecimal ONE_TENTH = new BigDecimal("0.1");

    private final MarketPriceRepository marketPriceRepository;

    public MarketPrice fetchLatestMarketPrice(Instrument instrument) {
        return marketPriceRepository.findByInstrumentId(instrument.getName());
    }

    public void handleMarketPrices(List<MarketPrice> marketPriceList) {
        for (MarketPrice price : marketPriceList) {
            MarketPrice afterCommision = addCommision(price);

            marketPriceRepository.save(afterCommision);
        }
    }

    public MarketPrice addCommision(MarketPrice price) {
        BigDecimal bidSubtract = percentage(price.bid(), ONE_TENTH);
        BigDecimal newBid = price.bid().subtract(bidSubtract);

        BigDecimal askAddition = percentage(price.ask(), ONE_TENTH);
        BigDecimal newAsk = price.ask().add(askAddition);

        return new MarketPrice(price.instrument(), newBid, newAsk, price.timestamp());
    }

    private static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
        return base.multiply(pct).divide(ONE_HUNDRED, RoundingMode.UP);
    }

}
