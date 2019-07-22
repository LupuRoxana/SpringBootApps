package com.springbootjpa.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootjpa.entity.User;
 
@Repository
public interface UserDAO extends CrudRepository<User, Long> {
 
//    public List<User> findByFullNameLike(String name);
// 
//    public List<User> findByDateOfBirthGreaterThan(Date date);
 
}
