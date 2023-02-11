package com.spring.andre.demo.utils;

public class Constants {

    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
	
	public static final String EMAIL_SENDER = "andreferreira6578@outlook.pt";
	public static final String WELCOME_EMAIL_TEMPLATE = "welcome-email";
	public static final String WELCOME_EMAIL_SUBJECT = "Seja Bem-vindo";
	
	public static final String RESET_EMAIL_TEMPLATE = "reset-password";
	public static final String RESET_EMAIL_SUBJECT = "Reset a sua password";
	
	public static final String AWS_MACHINE_ADDRESS_HOME_IMAGE = "https://my-bucket-image-spring.s3.us-east-2.amazonaws.com/";
	public static final String AWS_MACHINE_ADDRESS_PROFILE_IMAGE = "https://my-bucket-profile-image-spring-boot.s3.us-east-2.amazonaws.com/";
	
}
