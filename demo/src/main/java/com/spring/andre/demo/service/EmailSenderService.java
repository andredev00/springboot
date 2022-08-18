package com.spring.andre.demo.service;

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

import com.spring.andre.demo.model.Email;

@Service
public class EmailSenderService {
	
	private static final Logger log = LoggerFactory.getLogger(EmailSenderService.class);
	@Autowired
	JavaMailSender emailSender;
	@Autowired
	SpringTemplateEngine templateEngine;

	public void sendHtmlMessage(String from, String to, String url, String name, String emailTemplate, String subject) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariable("url", url);
		context.setVariable("name", name);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		String html = templateEngine.process(emailTemplate, context);
		helper.setText(html, true);

		log.info("Sending email: {} with html body: {}", emailTemplate, html);
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

	//TODO, provavelmente vai ficar aqui o envio do email do reset da password
	
}
