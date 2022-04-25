package com.spring.andre.demo.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.model.Client;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.ClientRepository;
import com.spring.andre.demo.repository.UserRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	// it will validate if our client or user exists in database, if yes, it will
	// return information about that especific user/client
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userRes = userRepository.findByEmail(email);
		if (userRes.isEmpty()) {
			Optional<Client> clientRes = clientRepository.findByEmail(email);
			if (userRes.isEmpty() && clientRes.isEmpty()) {
				throw new UsernameNotFoundException("Could not findUSer with email = " + email);
			}
			Client client = clientRes.get();
			return new org.springframework.security.core.userdetails.User(email, client.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
		}

		User user = userRes.get();
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}

}
