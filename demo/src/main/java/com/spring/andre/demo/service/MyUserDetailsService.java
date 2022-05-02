package com.spring.andre.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.UserRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	// it will validate if our client or user exists in database, if yes, it will
	// return information about that especific user/client
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userRes = userRepository.findByEmail(email);
		if (userRes.isEmpty())
			throw new UsernameNotFoundException("Could not findUser with email = " + email);

		User user = userRes.get();
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

}
