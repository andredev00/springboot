package com.spring.andre.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.andre.demo.model.RegisterHome;

public interface RegisterHomeRepository extends CrudRepository<RegisterHome, String> {

	RegisterHome findByLocation(String location);

}
