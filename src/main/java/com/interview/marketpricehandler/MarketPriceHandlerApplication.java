package com.interview.marketpricehandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.interview.marketpricehandler*")
public class MarketPriceHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketPriceHandlerApplication.class, args);
	}

}
