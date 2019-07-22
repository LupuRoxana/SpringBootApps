package com.springbootjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

/**
 * Hello world!
 */
@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class SpringBootJpa {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpa.class, args);
	}

}
