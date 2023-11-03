package com.interview.marketpricehandler.domain.service;

import com.interview.marketpricehandler.domain.model.enums.Instrument;
import com.interview.marketpricehandler.domain.port.MarketPriceRepository;
import com.interview.marketpricehandler.domain.model.MarketPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MarketPriceServiceTest {
    @Mock
    MarketPriceRepository marketPriceRepository;
    @InjectMocks
    MarketPriceService underTest;

    @Test
    void addCommision() {
        MarketPrice price = new MarketPrice(
                Instrument.EUR_USD,
                new BigDecimal("1000"),
                new BigDecimal("2000"),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 1, 1, 1));

        MarketPrice actual = underTest.addCommision(price);

        assertEquals(new BigDecimal("999.0"), actual.bid());
        assertEquals(new BigDecimal("2002.0"), actual.ask());
    }
}