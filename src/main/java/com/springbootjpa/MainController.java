package com.springbootjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springbootjpa.dao.UserDAO;
import com.springbootjpa.entity.User;

@RestController
public class MainController {

	@Autowired
	private UserDAO UserDAO;

	@RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
	public String index() {

		Iterable<User> all = UserDAO.findAll();

		StringBuilder sb = new StringBuilder();

		all.forEach(p -> sb.append(p.getFullName() + "<br>"));

		return sb.toString();
	}

}
