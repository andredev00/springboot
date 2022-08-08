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
	
//	@Transactional
//	@Modifying
//	@Query("update User u set u.address= :address, u.county = :county, u.dateBirth = :dateBirth, image_file_name = :imageFileName, image = :imagePath, language = :language, phone_number = :phoneNumber where u.id = :id")
//	void updateUser(String address, String county, Date dateBirth, String imageFileName, String imagePath, String language, int phoneNumber, Long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.address= :#{#user.address}, u.county = :#{#user.county}, u.dateBirth = :#{#user.dateBirth}, u.imageFileName = :#{#user.imageFileName}, u.imagePath = :#{#user.imagePath}, u.language = :#{#user.language}, u.phoneNumber = :#{#user.phoneNumber}, u.agentType =  :#{#user.agentType}, u.agentSociety = :#{#user.agentSociety} where u.id = :#{#id}")
	void updateUser(@Param("user") User user, @Param("id") String id);
	
	
	@Transactional
	@Query("select u from User u where u.id = :#{#id}")
	User findByGuid(@Param("id") String id);
	
}
