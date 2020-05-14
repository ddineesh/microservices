package com.dinesh.jpa.jpasamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dinesh.jpa.jpasamples.entity.User;
import com.dinesh.jpa.jpasamples.entity.manager.UserDaoService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);
	@Autowired
	private UserDaoService userDaoService;

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Diensh", "Admin");
		long userId = userDaoService.insert(user);
		logger.info("User created -> {} User Id -> {}",user,userId);
		User user1 = new User("Jack", "Developer");
		long userId1 = userDaoService.insert(user1);
		logger.info("User created -> {} User Id -> {}",user1,userId1);
	}

}
