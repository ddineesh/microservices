package com.dinesh.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dinesh.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.dinesh.rest.webservices.restfulwebservices.post.Post;
import com.dinesh.rest.webservices.restfulwebservices.post.PostRepository;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CacheManager cacheManager;

	@GetMapping(path = "/userList")
	@Cacheable(value = "User")
	public List<User> getListOfUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/userList/{id}")
	@Cacheable(value = "User")
	public Optional<User> findOne(@PathVariable int id) {
		Optional<User> userFound = userRepository.findById(id);
		if (!userFound.isPresent()) {
			throw new UserNotFoundException(" Id :=" + id);
		}
		return userFound;
	}

	@PostMapping(path = "/userList")
	@CacheEvict(value = "User", allEntries=true)
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/deleteUser/{id}")
	@CacheEvict(value = "User",allEntries = true)
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);

	}
	
	@PutMapping(path="/updateUser/{userId}")
	@CacheEvict(value="User", allEntries = true)
	public ResponseEntity<Object> updateUser(@PathVariable int userId,@RequestBody User user)
	{
		User updatedUser=userRepository.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(updatedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping(path = "clearCache")
    @Scheduled(cron = "0 0/30 * * * ?")              // execure after every 30 min
    public void clearCache(){
		for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();            // clear cache by name
        }
	
	/*
	 * @GetMapping(path = "/userList/{id}/posts") public List<Post>
	 * findUserPosts(@PathVariable int id) { Optional<User> userFound =
	 * userRepository.findById(id); if (!userFound.isPresent()) { throw new
	 * UserNotFoundException(" Id :=" + id); } return userFound.get().getPosts(); }
	 */

	/*
	 * @PostMapping(path = "/userList/{id}/posts") public ResponseEntity<Object>
	 * createPost(@PathVariable int id, @RequestBody Post post) { Optional<User>
	 * userFound = userRepository.findById(id); if (!userFound.isPresent()) { throw
	 * new UserNotFoundException(" Id :=" + id); }
	 * 
	 * User user = userFound.get();
	 * 
	 * post.setUser(user);
	 * 
	 * postRepository.save(post); URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (post.getId()) .toUri(); return ResponseEntity.created(location).build(); }
	 */
	}
}
