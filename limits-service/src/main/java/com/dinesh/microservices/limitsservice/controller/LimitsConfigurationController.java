package com.dinesh.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	Configuration configuration;
	
	@GetMapping(path="/limit-service")
	public LimitConfiguration retrieveLimitsFromConfiguration()
	{
		return new LimitConfiguration(configuration.getMinimum(),configuration.getMaximum());
	}
	
}
