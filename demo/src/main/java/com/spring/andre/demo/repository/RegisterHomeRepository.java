package com.spring.andre.demo.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.andre.demo.model.RegisterHome;

@Repository
public interface RegisterHomeRepository extends CrudRepository<RegisterHome, Serializable> {

	RegisterHome findByLocation(String location);

}
