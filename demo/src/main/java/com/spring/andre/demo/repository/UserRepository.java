package com.spring.andre.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.andre.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	
	Optional<User> findByEmail(String email);
	
	@Transactional
	@Query("select h from User h where permissions = 'ADMIN'")
	List<User> getAgents();
}
