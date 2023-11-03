package com.interview.marketpricehandler.adapter.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MarketPricesController.class)
class MarketPricesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void fetchLatestMarketPrice() throws Exception {
        String instrumentName = "EUR/USD";

        mockMvc.perform(get("/v1/prices/latest")
                        .param("instrumentName", instrumentName))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.instrument").value("EUR_USD"));
    }
}