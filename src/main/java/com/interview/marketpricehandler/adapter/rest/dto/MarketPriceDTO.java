package com.interview.marketpricehandler.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.interview.marketpricehandler.domain.model.enums.Instrument;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class MarketPriceDTO {
    Instrument instrument;
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    BigDecimal bid;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    BigDecimal ask;
    LocalDateTime timestamp;
}
