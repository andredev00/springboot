package com.spring.imobiliaria.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import com.spring.imobiliaria.enums.PermissionsEnum;
import com.spring.imobiliaria.service.UserService;

public class ServiceUtils {

	UserService userService;

	private ServiceUtils() {
		throw new UnsupportedOperationException("This is a utility class and method cannot be instantiated");
	}

	public static int generateRandomInt() {
		int parameterValue = 0;
		Random rand = new Random();
		parameterValue = rand.nextInt(10000);

		return parameterValue;
	}

	public static String formatterPriceEuro(String price) {
		Locale l = Locale.getDefault();

		NumberFormat formatter = NumberFormat.getNumberInstance(l);
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);
		return formatter.format(Integer.valueOf(price));
	}	

	public static Enum<PermissionsEnum> convertToEnum(String role) {
		if (role.equals("ADMIN")) {
			return PermissionsEnum.ADMIN;
		} else {
			return PermissionsEnum.USER;
		}
	}

}
