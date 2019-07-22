package com.springbootjpa.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootjpa.entity.Address;


public interface AddressDAO extends JpaRepository<Address, Long>{
	Page<Address> findByUserId(Long userId, Pageable pageable);
	Optional<Address> findByIdAndUserId(Long id, Long userId);
}
