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
	
//	@RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
//	public String index() {
//
//		Iterable<User> all = UserDAO.findAll();
//
//		StringBuilder sb = new StringBuilder();
//
//		all.forEach(p -> sb.append(p.getFullName() + "<br>"));
//
//		return sb.toString();
//	}
//	
//	 @GetMapping
//	    public Iterable<User> findAll() {
//	        return userRepository.findAll();
//	    }
//
//	    @GetMapping(path = "/{username}")
//	    public User find(@PathVariable("username") String username) {
//	        return userRepository.findOne(username);
//	    }
//
//	    @PostMapping(consumes = "application/json")
//	    public User create(@RequestBody User user) {
//	        return userRepository.save(user);
//	    }
//
//	    @DeleteMapping(path = "/{username}")
//	    public void delete(@PathVariable("username") String username) {
//	        userRepository.delete(username);
//	    }
//
//	    @PutMapping(path = "/{username}")
//	    public User update(@PathVariable("username") String username, @RequestBody User user) throws BadHttpRequest {
//	        if (userRepository.exists(username)) {
//	            user.setFullName(username);
//	            return userRepository.save(user);
//	        } else {
//	            throw new BadHttpRequest();
//	        }
//	    }
//	@PostMapping(path = "/users") // Map ONLY POST Requests
//	public @ResponseBody String createUserByIdAndName(@RequestParam Long id, @RequestParam String name) {
//		// @ResponseBody means the returned String is the response, not a view name
//		// @RequestParam means it is a parameter from the GET or POST request
//
//		User n = new User();
//		n.setId(id);
//		n.setFullName(name);
//		userRepository.save(n);
//		return "Saved";
//	}
//	 @DeleteMapping("/users/{userId}")
//	 public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
//		 return UserDAO.findById(userId).map(user -> {
//			 UserDAO.delete(user);
//	         return UserDAO.ok().build();
//	     }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
//	 }
	

}
