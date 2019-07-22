package com.springbootjpa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.exception.ResourceNotFoundException;
import com.springbootjpa.dao.UserDAO;
import com.springbootjpa.entity.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO UserDAO;

	@RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
	public String index() {

		Iterable<User> all = UserDAO.findAll();

		StringBuilder sb = new StringBuilder();

		all.forEach(p -> sb.append(p.getFullName() + "<br>"));

		return sb.toString();
	}
	
	@GetMapping("/users")
    public Page<User> getAllusers(Pageable pageable) {
        return UserDAO.findAll(pageable);
    }
	
	 @PostMapping("/users")
	 public User createUser(@Valid @RequestBody User user) {
	    return UserDAO.save(user);
	 }
	 
	 @PutMapping("/users/{userId}")
	 public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
	    return UserDAO.findById(userId).map(user -> {
	    	user.setFullName(userRequest.getFullName());
	        return UserDAO.save(user);
	    }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
	 }
	 
//	 @DeleteMapping("/users/{userId}")
//	 public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
//		 return UserDAO.findById(userId).map(user -> {
//			 UserDAO.delete(user);
//	         return UserDAO.ok().build();
//	     }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
//	 }
	

}
