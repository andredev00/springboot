package com.spring.andre.demo.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.andre.demo.model.Home;

@Repository
public interface HomeRepository extends CrudRepository<Home, Serializable> {

	Home findByLocation(String location);

}
