package com.dinesh.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dinesh.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping(path = "/jpa/userList")
	public List<User> getListOfUsers() {
		return userDaoService.getListOfUsers();
	}

	@GetMapping(path = "/jpa/userList/{id}")
	public User findOne(@PathVariable int id) {
		User userFound= userDaoService.findUser(id);
		if(null==userFound)
		{
			throw new UserNotFoundException(" Id :="+id);
		}
		return userFound;
	}

	@PostMapping(path = "/jpa/userList")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/deleteUser/{id}")
	public void deleteUser(@PathVariable int id) {
		User deletedUser= userDaoService.deleteUser(id);
		if(null==deletedUser)
		{
			throw new UserNotFoundException("Id :="+id);
		}
	}
}
