package com.spring.andre.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.andre.demo.model.Client; 
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.ClientRepository;
import com.spring.andre.demo.repository.UserRepository;
import com.spring.andre.demo.service.MyClientDetails;
import com.spring.andre.demo.service.MyUserDetails;

@Service
public class UsersDetailsService implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Client client = clientRepository.findByUsername(username);
		
		if(client == null) {
			User user = userRepository.findByUsername(username);
			
			return new MyUserDetails(user);
		} else if (userRepository.findByUsername(username) == null) {
			Client user = clientRepository.findByUsername(username);
			
			
			return new MyClientDetails(user);
		}
		return new MyClientDetails(client);
	}

}
