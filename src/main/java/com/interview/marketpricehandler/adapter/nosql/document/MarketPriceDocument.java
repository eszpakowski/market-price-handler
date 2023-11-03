package com.interview.marketpricehandler.adapter.nosql.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Document
public class MarketPriceDocument {
    @Id
    private String instrumentName;
    private BigDecimal bid;
    private BigDecimal ask;
    private LocalDateTime timestamp;
}