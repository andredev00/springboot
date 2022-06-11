package com.spring.andre.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Email {
	
	@Schema(example = "andreferreira6578@gmail.com")
	private String to;
	@Schema(example = "andreferreira6578@gmail.com")
	private String from;
	@Schema(example = "Welcome Email from CodingNConcepts")
	private String subject;
	@Schema(example = "Thank you for subscribing to our channel.")
	private String text;
	@Schema(example = "welcome-email.html")
	private String template;
	private String url;
	private String name;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
