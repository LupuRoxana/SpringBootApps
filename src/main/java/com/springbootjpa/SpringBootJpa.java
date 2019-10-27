package com.springbootjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import com.springbootjpa.dao.UserRepository;
import com.springbootjpa.entity.User;

/**
 * Hello world!
 */
@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class SpringBootJpa {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpa.class, args);
		
//		User user = new User();
//		user.setFullName("POPESCU");
//		user.setId((long) 137325442);
//		
//		UserRepository user2 = null;
//		//user2.createUserByIdAndName((long)1243252342, "Ionescu");
//		
//		System.out.println(user);
//		System.out.println(user2);
	}

}
