package com.spring.andre.demo.utils;

import com.spring.andre.demo.enums.ERole;

public class ERoleConverter {

	public static String roleConverter(ERole role) {
		String roleString = null;
		
		switch (role) {
			case ROLE_USER:
				 roleString = "USER";
				break;
			case ROLE_ADMIN:
				roleString = "ADMIN";
				break;
			default:
				break;
		}
		return roleString;
	}
	
}
