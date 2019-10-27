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
    
//    @GetMapping("/users/{userId}/address")
//    public Page<Address> getAllAddressByuserId(@PathVariable (value = "userId") Long userId,
//                                                Pageable pageable) {
//        return AddressRepository.findByUserId(userId, pageable);
//    }
//
//    @PostMapping("/users/{userId}/address")
//    public Address createddress(@PathVariable (value = "userId") Long userId,
//                                 @Valid @RequestBody Address address) {
//        return UserRepository.findById(userId).map(user -> {
//        	address.setUser(user);
//            return AddressDAO.save(address);
//        }).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
//    }
//
//    @PutMapping("/users/{userId}/address/{addressId}")
//    public Address updateAddress(@PathVariable (value = "userId") Long userId,
//                                 @PathVariable (value = "addressId") Long addressId,
//                                 @Valid @RequestBody Address addressRequest) {
//        if(!UserRepository.existsById(userId)) {
//            throw new ResourceNotFoundException("userId " + userId + " not found");
//        }
//
//        return AddressRepository.findById(addressId).map(address -> {
//            address.setText(addressRequest.getText());
//            return AddressDAO.save(address);
//        }).orElseThrow(() -> new ResourceNotFoundException("addressId " + addressId + "not found"));
//    }
//
//    @DeleteMapping("/users/{userId}/address/{addressId}")
//    public ResponseEntity<?> deleteAddress(@PathVariable (value = "userId") Long userId,
//                              @PathVariable (value = "addressId") Long addressId) {
//        return AddressRepository.findByIdAndUserId(addressId, userId).map(address -> {
//        	AddressRepository.delete(address);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId + " and userId " + userId));
//    }
}

