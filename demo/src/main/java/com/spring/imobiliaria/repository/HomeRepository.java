package com.spring.imobiliaria.repository;

import java.io.Serializable;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.imobiliaria.model.Home;

@Repository
public interface HomeRepository extends JpaRepository<Home, Serializable> {

	Home findByLocation(String location);

	@Transactional
	@Query("select h from Home h where id = :id")
	ArrayList<Home> findOne(String id);
	
	@Transactional
	@Query("select h from Home h where image_file_name = :imageName")
	Home findByTitle(String imageName);

}
