package com.springbootjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springbootjpa.dao.AddressRepository;
import com.springbootjpa.dao.UserRepository;


@RestController
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	

}
