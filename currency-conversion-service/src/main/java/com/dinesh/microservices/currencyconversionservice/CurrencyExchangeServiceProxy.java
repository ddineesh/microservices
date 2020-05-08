package com.dinesh.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dinesh.microservices.currencyconversionservice.bean.CurrencyConversionBean;

/*@FeignClient(name = "currency-exchange-service",url = "localhost:8000")*/
//The below line will make the call through naming server(eureka)
/*@FeignClient(name = "currency-exchange-service")*/
//The below line will make feign call through Zuul
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	/* @GetMapping(path = "/currency-exchange/from/{from}/to/{to}") */
	//The below line will make feign call through Zuul
	@GetMapping(path = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
	
}
