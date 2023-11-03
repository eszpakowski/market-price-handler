package com.interview.marketpricehandler.adapter.message;

import com.interview.marketpricehandler.domain.model.MarketPrice;
import com.interview.marketpricehandler.domain.model.enums.Instrument;
import com.interview.marketpricehandler.domain.service.MarketPriceService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Disabled
@SpringBootTest
class MqMarketPriceSubscriberIntegrationTest {

    @Autowired
    MqMarketPriceSubscriber subscriber;

    @Autowired
    MarketPriceService marketPriceService;

    @Test
    void testInsert() {
        subscriber.onMessage("101, EUR/USD, 1.5000,1.6000,01-06-2020 12:02:01:001");

        MarketPrice price = marketPriceService.fetchLatestMarketPrice(Instrument.fromString("EUR/USD"));

        assertNotEquals(null, price);
    }
}