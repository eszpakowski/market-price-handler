package com.interview.marketpricehandler.adapter.message;

import com.interview.marketpricehandler.domain.model.MarketPrice;
import com.interview.marketpricehandler.domain.model.enums.Instrument;
import com.interview.marketpricehandler.domain.service.MarketPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MqMarketPriceSubscriber { //implements MqListener {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss:SSS");
    private static final String COMMA = ",";

    private final MarketPriceService marketPriceService;

    public void onMessage(String message) {
        List<MarketPrice> marketPriceList = message.lines()
                .map(line -> line.split(COMMA))
                .filter(this::isValid)
                .map(this::mapToMarketPrice)
                .toList();

        marketPriceService.handleMarketPrices(marketPriceList);
    }

    private boolean isValid(String[] values) {
        // additional validation
        return true;
    }

    private MarketPrice mapToMarketPrice(String[] values) {
        return new MarketPrice(
                Instrument.fromString(values[1].trim()),
                new BigDecimal(values[2].trim()),
                new BigDecimal(values[3].trim()),
                LocalDateTime.parse(values[4].trim(), FORMATTER)
        );
    }
}
