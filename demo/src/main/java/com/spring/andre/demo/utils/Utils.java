package com.spring.andre.demo.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Utils {
	public static class Security {
		public final static String[] WHITELIST = { "/sign-up/client", "/sign-up/user", "/swagger-ui.html",
				"/swagger-ui/", "/swagger-ui", "/login", "/swagger-ui/" };
	}

	public static class JwtFilter {
		public final static String[] endpointList = { "v2/api-docs", "swagger", "/h2-console", "/sign-up",
				"/login", "/allHomes", "register", "/uploadFile", "/deleteFile" };
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
		
		String finalPrice = formatter.format(price);
		return finalPrice;
	}
	
}
