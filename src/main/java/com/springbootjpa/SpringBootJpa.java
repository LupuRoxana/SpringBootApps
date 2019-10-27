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
		
		Runnable runnable1 = () -> { 
		    for(int i=0; i< 10; i++) {
		    	System.out.println("-- Thread 1 --");
		    }
		};
		
		Runnable runnable2 = () -> { 
		    for(int i=0; i< 10; i++) {
		    	System.out.println("-- Thread 2 --");
		    }
		};
		
		Runnable runnable3 = () -> { 
		    for(int i=0; i< 10; i++) {
		    	System.out.println("-- Thread 3 --");
		    }
		};
		
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		Thread thread2 = new Thread(runnable2);
		thread2.start();
		Thread thread3 = new Thread(runnable3);
		thread3.start();
		
		while(thread1.isAlive() && thread2.isAlive() && thread3.isAlive()) {
			System.out.println("Thread has not finished");
	    } 
	        
		System.out.println("Finished");
	    		
		
				
	}

}
