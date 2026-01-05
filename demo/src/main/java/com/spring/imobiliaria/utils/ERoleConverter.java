package com.spring.imobiliaria.utils;

import com.spring.imobiliaria.enums.PermissionsEnum;

public class ERoleConverter {

	public static String roleConverter(PermissionsEnum role) {
		String roleString = null;
		
		switch (role) {
			case USER:
				 roleString = "USER";
				break;
			case ADMIN:
				roleString = "ADMIN";
				break;
			default:
				break;
		}
		return roleString;
	}
	
}
