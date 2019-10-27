package com.springbootjpa.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.springbootjpa.dao.AddressRepository;
import com.springbootjpa.dao.UserRepository;
import com.springbootjpa.entity.Address;
import com.springbootjpa.entity.User;
 
@Component
public class DataInit implements ApplicationRunner {
 
    private UserRepository userRepository;
 
    @Autowired
	private AddressRepository addressRepository;
    @Autowired
    public DataInit(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
        
    	long count = userRepository.count();
 
        if (count == 0) {
        	
      

        }
 
    }
     
}
