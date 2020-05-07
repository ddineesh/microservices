package com.dinesh.microservices.currencyexchangeservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String helloWorld()
	{
		return "Hello Wolrd from currency-exchange-service";
	}
	
}
