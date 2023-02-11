package com.spring.andre.demo.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class Utils {
	
    private Utils() {
        throw new UnsupportedOperationException("This is a utility class and method cannot be instantiated");
    }
	
    @Getter
    @Setter
	public static class Security {
		
	    public Security() {
	        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	    }
		
		protected final String[] WHITELIST = { "/sign-up/client", "/sign-up/user", "/swagger-ui.html",
				"/swagger-ui/", "/swagger-ui", "/login", "/swagger-ui/" };
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

}
