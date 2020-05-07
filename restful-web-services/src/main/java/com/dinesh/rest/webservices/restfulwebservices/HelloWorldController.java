package com.dinesh.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	/*@RequestMapping(method = RequestMethod.GET,path = "/hello-world")
	public String helloWorld()
	{
		return "Hello World from different Controller";
	}*/
	@GetMapping(path = "/hello-world")
	public String helloWorld()
	{
		return " Hello World from different Controller";
	}
	@GetMapping(path="/hello-world-bean")
	public HelloWolrdBean helloWorldBean()
	{
		return new HelloWolrdBean("Hello Wolrd from Bean");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public  HelloWolrdBean helloWorldPathVariable(@PathVariable String name)
	{
		return new HelloWolrdBean(String.format("Hello World from , %s",name));
	}
}
