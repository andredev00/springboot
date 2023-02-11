package com.spring.andre.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.andre.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	@Transactional
	@Query("select h from User h where permissions = 'ADMIN'")
	List<User> getAgents();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.address= :#{#user.address}, u.county = :#{#user.county}, u.dateBirth = :#{#user.dateBirth}, u.imageFileName = :#{#user.imageFileName}, u.imagePath = :#{#user.imagePath}, u.language = :#{#user.language}, u.phoneNumber = :#{#user.phoneNumber}, u.agentType =  :#{#user.agentType}, u.agentSociety = :#{#user.agentSociety} where u.id = :#{#id}")
	void updateUser(@Param("user") User user, @Param("id") String id);
	
	
	@Transactional
	@Query("select u from User u where u.id = :#{#id}")
	User findByGuid(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.password= :#{#password} where u.id = :#{#id}")
	void updatePassword(@Param("password") String password, @Param("id") String id);

	@Transactional
	@Modifying(clearAutomatically =  true)
	@Query("update User u set u.active = 'true' where u.id = :#{#id}")
	void activeAccount(String id);
	
	@Transactional
	@Query("select h from User h where h.name = :#{#name} and h.id = :#{#id}")
	User getAgentDetail(@Param("name") String name, @Param("id") String id);

	@Transactional
	@Query("select u.imageFileName from User u where u.id = :uuid")
	String getProfileImage(String uuid);
	
}
