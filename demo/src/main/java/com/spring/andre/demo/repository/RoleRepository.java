package com.spring.andre.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.andre.demo.model.Role;
import com.spring.andre.demo.utils.ERole;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findByName(ERole name);

}
