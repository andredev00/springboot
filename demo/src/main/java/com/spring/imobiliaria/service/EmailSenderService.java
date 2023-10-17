package com.spring.imobiliaria.service;

import static com.spring.imobiliaria.utils.Constants.EMAIL_SENDER;
import static com.spring.imobiliaria.utils.Constants.RESET_EMAIL_SUBJECT;
import static com.spring.imobiliaria.utils.Constants.RESET_EMAIL_TEMPLATE;
import static com.spring.imobiliaria.utils.Constants.WELCOME_EMAIL_SUBJECT;
import static com.spring.imobiliaria.utils.Constants.WELCOME_EMAIL_TEMPLATE;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.spring.imobiliaria.model.Email;

@Service
public class EmailSenderService {

	private static final Logger log = LoggerFactory.getLogger(EmailSenderService.class);
	@Autowired
	JavaMailSender emailSender;
	@Autowired
	SpringTemplateEngine templateEngine;

	public void sendHtmlMessage(String name, String email, String url) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariable("url", url);
		context.setVariable("name", name);
		helper.setFrom(EMAIL_SENDER);
		helper.setTo(email);
		helper.setSubject(WELCOME_EMAIL_SUBJECT);
		String html = templateEngine.process(WELCOME_EMAIL_TEMPLATE, context);
		helper.setText(html, true);

		log.info("Sending register email: {} with html body: {}", WELCOME_EMAIL_TEMPLATE, html);
		emailSender.send(message);
	}
	
	public void sendResetPassword(String name, String email, String url) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariable("url", url);
		context.setVariable("name", name);
		helper.setFrom(EMAIL_SENDER);
		helper.setTo(email);
		helper.setSubject(RESET_EMAIL_SUBJECT);
		String html = templateEngine.process(RESET_EMAIL_TEMPLATE, context);
		helper.setText(html, true);

		log.info("Sending reset email: {} with html body: {}", RESET_EMAIL_TEMPLATE, html);
		emailSender.send(message);

	}

	public void sendSimpleMessage(Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(email.getFrom());
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getText());

		log.info("Sending email: {} with text body: {}", email, email.getText());
		emailSender.send(message);
	}
}
