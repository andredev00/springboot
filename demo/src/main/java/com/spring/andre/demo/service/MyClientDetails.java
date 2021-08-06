package com.spring.andre.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.andre.demo.model.Client;

public class MyClientDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1156594590593954145L;
	
	private Client client;
	
	public MyClientDetails() {
		
	}

	public MyClientDetails(Client client) {
		super();
		this.client = client;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return client.getPassword();
	}

	@Override
	public String getUsername() {
		return client.getUsername();
	}
	
	public String getName() {
		return client.getName();
	}
	
	/*public Long getId() {
		return client.getId();
	}*/

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
