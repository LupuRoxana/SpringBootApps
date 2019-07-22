package com.springbootjpa.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.springbootjpa.dao.UserDAO;
import com.springbootjpa.entity.Address;
import com.springbootjpa.entity.User;
 
@Component
public class DataInit implements ApplicationRunner {
 
    private UserDAO UserDAO;
 
    @Autowired
    public DataInit(UserDAO UserDAO) {
        this.UserDAO = UserDAO;
    }
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
        
    	long count = UserDAO.count();
 
        if (count == 0) {
        	
            User p1 = new User(); 
            p1.setFullName("John Snow");
 
            User p2 = new User(); 
            p2.setFullName("Will Smith");

            Address address1 = new Address();
            Address address2 = new Address();
            
            address1.setText("Deva, Strada Visului, Nr 14");
            address2.setText("Timisoara, Strada Visului, Nr 22");
            
            address1.setUser(p1);
            address2.setUser(p2);
            
            p1.getAddress().add(address1);
            p1.getAddress().add(address2);
            
            UserDAO.save(p1);

        }
 
    }
     
}
