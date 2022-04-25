package com.spring.andre.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.andre.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	
	Optional<User> findByEmail(String email);
	
}
