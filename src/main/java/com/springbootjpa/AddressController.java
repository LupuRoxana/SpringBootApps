package com.springbootjpa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.exception.ResourceNotFoundException;
import com.springbootjpa.dao.AddressRepository;
import com.springbootjpa.dao.UserRepository;
import com.springbootjpa.entity.Address;

@RestController
public class AddressController {

	@Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users/{userId}/comments")
    public Page<Address> getAllAddressByUserId(@PathVariable (value = "userId") Long userId,
                                                Pageable pageable) {
        return addressRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/comments")
    public Address createAddress(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Address address) {
        return userRepository.findById(userId).map(user -> {
            address.setUser(user);
            return addressRepository.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
    
}

