package com.interview.marketpricehandler.adapter.rest;

import com.interview.marketpricehandler.adapter.rest.dto.MarketPriceDTO;
import com.interview.marketpricehandler.domain.model.MarketPrice;
import com.interview.marketpricehandler.domain.model.enums.Instrument;
import com.interview.marketpricehandler.domain.service.MarketPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/prices")
public class MarketPricesController {
    private final MarketPriceService marketPriceService;

    @GetMapping(value = "latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public MarketPriceDTO fetchLatestMarketPrice(String instrumentName) {
        log.info(String.format("Fetching latest market price for instrument [instrumentName=%s]", instrumentName));

        MarketPrice price = marketPriceService.fetchLatestMarketPrice(Instrument.fromString(instrumentName));

        MarketPriceDTO result = toDto(price);
        log.info(String.format("Fetched latest market price for instrument [result=%s]", result.toString()));
        return result;
    }

    private MarketPriceDTO toDto(MarketPrice price) {
        return MarketPriceDTO.builder()
                .instrument(price.instrument())
                .bid(price.bid())
                .ask(price.ask())
                .timestamp(price.timestamp())
                .build();
    }
}
