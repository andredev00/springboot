package com.spring.andre.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.andre.demo.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>  {


    
}
