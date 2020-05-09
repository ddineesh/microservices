package com.dinesh.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	Environment environment;

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		/*
		 * ExchangeValue exchangeValue = new ExchangeValue(2320L, from, to,
		 * BigDecimal.valueOf(65.43));
		 */
		ExchangeValue exchangeValue =exchangeValueRepository.findByFromAndTo(from, to);
		
		logger.info("exchangeValue -> {}",exchangeValue);
		
		exchangeValue.setServerPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
}
