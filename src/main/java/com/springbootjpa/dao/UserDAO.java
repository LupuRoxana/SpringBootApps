package com.springbootjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootjpa.entity.User;
 
@Repository
public interface UserDAO extends JpaRepository<User, Long> {
 
}
