package com.spring.andre.demo.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.andre.demo.model.Email;
import com.spring.andre.demo.service.EmailSenderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class EmailSenderController {

	@Autowired
	EmailSenderService emailSenderService;
	
	@PostMapping("/email/send/html")
    public void sendHtmlMessage(@RequestBody Email email) throws MessagingException {
        emailSenderService.sendHtmlMessage("", "", null);
    }

    @PostMapping("email/send")
    public void sendSimpleMessage(@RequestBody Email email) {
        emailSenderService.sendSimpleMessage(email);
    }
	
}
