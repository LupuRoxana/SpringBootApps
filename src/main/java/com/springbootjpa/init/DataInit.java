package com.springbootjpa.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.springbootjpa.dao.UserDAO;
import com.springbootjpa.entity.User;
 
@Component
public class DataInit implements ApplicationRunner {
 
    private UserDAO UserDAO;
 
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 
    @Autowired
    public DataInit(UserDAO UserDAO) {
        this.UserDAO = UserDAO;
    }
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
        
    	long count = UserDAO.count();
 
        if (count == 0) {
            User p1 = new User();
 
            p1.setFullName("John");
 
            Date d1 = df.parse("1980-12-20");
            p1.setDateOfBirth(d1);
            //
            User p2 = new User();
 
            p2.setFullName("Smith");
            Date d2 = df.parse("1985-11-11");
            p2.setDateOfBirth(d2);
 
            UserDAO.save(p1);
            UserDAO.save(p2);
        }
 
    }
     
}
