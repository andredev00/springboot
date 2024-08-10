package com.spring.imobiliaria.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.imobiliaria.model.HomeImage;

@Repository
public interface HomeImageRepository extends JpaRepository<HomeImage, Serializable> {

	List<HomeImage> findByHomeId(String id);

}
