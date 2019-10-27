package com.springbootjpa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.exception.ResourceNotFoundException;
import com.springbootjpa.dao.UserRepository;
import com.springbootjpa.entity.User;


@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/users")
	public Page<User> getAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
		
	}

	@PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
	
	@PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setAge(userRequest.getAge());
            user.setOcupation(userRequest.getOcupation());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found"));
    }
	
}
