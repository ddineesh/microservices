package com.dinesh.rest.webservices.restfulwebservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableCaching
@Controller
public class RestfulWebServicesApplication implements CommandLineRunner{

	private static final Logger logger=LoggerFactory.getLogger(RestfulWebServicesApplication.class);
	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
				
	}
	
	public void run(String... args) throws Exception {
        logger.info("\n----Begin logging BooksServiceApplication----");

        logger.info("----System Properties from VM Arguments----");
        logger.info("server.port: " + System.getProperty("server.port"));
        logger.info("----Program Arguments----");
        for (String arg : args) {
            logger.info(arg);
        }

        if (environment != null) {
            getActiveProfiles();
            logger.info("----Environment Properties----");
            logger.info("server.port: " + environment.getProperty("server.port"));
            logger.info("nl.amis.environment: " + environment.getProperty("nl.amis.environment"));
            logger.info("spring.datasource.url: " + environment.getProperty("spring.datasource.url"));
            logger.info("spring.datasource.username: " + environment.getProperty("spring.datasource.username"));
            logger.info("spring.datasource.password: " + environment.getProperty("spring.datasource.password"));
            logger.info("spring.jpa.database-platform: " + environment.getProperty("spring.jpa.database-platform"));
            logger.info("spring.jpa.hibernate.ddl-auto: " + environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        }

        logger.info("----End logging RestfulWebServicesApplication----");
    }

	@RequestMapping(method =RequestMethod.GET,path = "/hello")
	@ResponseBody
	public String helloWorld()
	{
		return "Hello World!!!!!";
	}
	

private void getActiveProfiles() {
    for (final String profileName : environment.getActiveProfiles()) {
        logger.info("Currently active profile - " + profileName);
    }
}
	
	
}
