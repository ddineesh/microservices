package com.dinesh.microservices.springcloudconfigserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

@GetMapping(path = "/hello-world")
public String helloWorld()
{
	return "Hello World from cloud config server";
}
	
}

