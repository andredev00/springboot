package com.spring.andre.demo.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.andre.demo.dto.InputDto;
import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.User;

public interface UserService {
	
	void createUser(UserDTO userDTO, MultipartFile multipartFile);
	
	List<User> getAgents();

	List<InputDto> getDistinctAgentType();
	
	List<InputDto> getDistinctName();
	
	List<InputDto> getDistinctCounty();
	
	String getProfileImage(String uuid);
	
	User getAgentDetail(String name, String uuid);
	
	void activeAccount(String uuid);
	
	void resetPassword(String password, String uuid);
	
	User editUser(UserDTO userDTO, MultipartFile multipartFile, String uuid);
}  
