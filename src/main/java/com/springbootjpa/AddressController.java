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
import com.springbootjpa.dao.UserDAO;
import com.springbootjpa.entity.Address;
import com.springbootjpa.dao.AddressDAO;
import com.springboot.jpa.exception.ResourceNotFoundException;

public class AddressController {

	@Autowired
    private AddressDAO AddressDAO;

    @Autowired
    private UserDAO UserDAO;

    @GetMapping("/users/{userId}/address")
    public Page<Address> getAllAddressByuserId(@PathVariable (value = "userId") Long userId,
                                                Pageable pageable) {
        return AddressDAO.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/address")
    public Address createddress(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Address address) {
        return UserDAO.findById(userId).map(user -> {
        	address.setUser(user);
            return AddressDAO.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/address/{addressId}")
    public Address updateAddress(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "addressId") Long addressId,
                                 @Valid @RequestBody Address addressRequest) {
        if(!UserDAO.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }

        return AddressDAO.findById(addressId).map(address -> {
            address.setText(addressRequest.getText());
            return AddressDAO.save(address);
        }).orElseThrow(() -> new ResourceNotFoundException("addressId " + addressId + "not found"));
    }

    @DeleteMapping("/users/{userId}/address/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable (value = "userId") Long userId,
                              @PathVariable (value = "addressId") Long addressId) {
        return AddressDAO.findByIdAndUserId(addressId, userId).map(address -> {
        	AddressDAO.delete(address);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId + " and userId " + userId));
    }
}

