package com.dinesh.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dinesh.microservices.currencyconversionservice.CurrencyExchangeServiceProxy;
import com.dinesh.microservices.currencyconversionservice.bean.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	@GetMapping(path = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		Map<String,String> uriVariables=new HashMap<String,String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().
				getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
						CurrencyConversionBean.class,uriVariables);
		
		CurrencyConversionBean currencyConversionRespons=responseEntity.getBody();
		
		BigDecimal convertedValue=quantity.multiply(currencyConversionRespons.getConversionMultiple());
		
		return new CurrencyConversionBean(currencyConversionRespons.getId(),from,to,
				currencyConversionRespons.getConversionMultiple(),quantity,convertedValue,currencyConversionRespons.getServerPort());
	}
	
	@GetMapping(path = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		Map<String,String> uriVariables=new HashMap<String,String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		//calling other microservices through feign client
		CurrencyConversionBean currencyConversionRespons=currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
		
		BigDecimal convertedValue=quantity.multiply(currencyConversionRespons.getConversionMultiple());
		
		return new CurrencyConversionBean(currencyConversionRespons.getId(),from,to,
				currencyConversionRespons.getConversionMultiple(),quantity,convertedValue,currencyConversionRespons.getServerPort());
	}

}
