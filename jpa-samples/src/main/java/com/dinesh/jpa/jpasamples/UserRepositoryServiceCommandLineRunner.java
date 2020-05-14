package com.dinesh.jpa.jpasamples;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dinesh.jpa.jpasamples.entity.User;
import com.dinesh.jpa.jpasamples.repository.UserRepository;

@Component
public class UserRepositoryServiceCommandLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryServiceCommandLineRunner.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Suba", "Admin");
		userRepository.save(user);
		logger.info("User created -> {} User Id -> {}",user,user.getId());
		User user1 = new User("Jill", "Developer");
		userRepository.save(user1);
		logger.info("User created -> {} User Id -> {}",user1,user1.getId());
		
		//findById
		
		Optional<User> user3 = userRepository.findById(1L);
		
		logger.info("User find by Id 1 {}",user3);
		
		List<User> users=userRepository.findAll();
		
		logger.info("All Users {}",users);
	}

}
