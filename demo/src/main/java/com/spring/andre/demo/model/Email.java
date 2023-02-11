package com.spring.andre.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Email {
	
//	@Schema(example = "andreferreira6578@gmail.com")
	private String to;
//	@Schema(example = "andreferreira6578@gmail.com")
	private String from;
//	@Schema(example = "Welcome Email from CodingNConcepts")
	private String subject;
//	@Schema(example = "Thank you for subscribing to our channel.")
	private String text;
//	@Schema(example = "welcome-email.html")
	private String template;
	private String url;
	private String name;
}
