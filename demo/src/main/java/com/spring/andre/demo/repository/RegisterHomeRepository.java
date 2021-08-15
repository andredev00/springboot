package com.spring.andre.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.andre.demo.model.RegisterHome;

@Repository
public interface RegisterHomeRepository extends CrudRepository<RegisterHome, String> {

	RegisterHome findByLocation(String location);

}
