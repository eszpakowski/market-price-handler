package com.interview.marketpricehandler.adapter.message;

import com.interview.marketpricehandler.domain.model.MarketPrice;
import com.interview.marketpricehandler.domain.model.enums.Instrument;
import com.interview.marketpricehandler.domain.service.MarketPriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MqMarketPriceSubscriberTest {

    private static final String LINE_1 = "100, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
    private static final String LINE_2 = "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002";
    private static final String NEWLINE = " \n ";
    @Mock
    MarketPriceService marketPriceService;
    @InjectMocks
    MqMarketPriceSubscriber underTest;

    @Test
    void givenValidSingleLineMessage_whenOnMessage_thenShouldParseExpectedPrice() {
        //given
        MarketPrice price = new MarketPrice(
                Instrument.EUR_USD,
                new BigDecimal("1.1000"),
                new BigDecimal("1.2000"),
                LocalDateTime.parse("01-06-2020 12:01:01:001", MqMarketPriceSubscriber.FORMATTER));

        //when
        underTest.onMessage(LINE_1);

        //then
        verify(marketPriceService).handleMarketPrices(List.of(price));
    }

    @Test
    void givenValidMultiLineMessage_whenOnMessage_thenShouldParseExpectedPrice() {
        //given
        MarketPrice price1 = new MarketPrice(
                Instrument.EUR_USD,
                new BigDecimal("1.1000"),
                new BigDecimal("1.2000"),
                LocalDateTime.parse("01-06-2020 12:01:01:001", MqMarketPriceSubscriber.FORMATTER));

        MarketPrice price2 = new MarketPrice(
                Instrument.EUR_JPY,
                new BigDecimal("119.60"),
                new BigDecimal("119.90"),
                LocalDateTime.parse("01-06-2020 12:01:02:002", MqMarketPriceSubscriber.FORMATTER));

        //when
        underTest.onMessage(LINE_1 + NEWLINE + LINE_2);

        //then
        verify(marketPriceService).handleMarketPrices(List.of(price1, price2));
    }
}