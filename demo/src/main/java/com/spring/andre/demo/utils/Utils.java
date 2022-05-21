package com.spring.andre.demo.utils;

public class Utils {
	public static class Security {
		public final static String[] WHITELIST = { "/sign-up/client", "/sign-up/user", "/swagger-ui.html",
				"/swagger-ui/", "/swagger-ui", "/login", "/swagger-ui/" };
	}

	public static class JwtFilter {
		public final static String[] endpointList = { "v2/api-docs", "swagger", "/h2-console", "/sign-up",
				"/login", "/allHomes", "register", "/uploadFile", "/deleteFile" };
	}
}
