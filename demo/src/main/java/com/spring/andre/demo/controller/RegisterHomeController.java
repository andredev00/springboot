package com.spring.andre.demo.controller;

import com.spring.andre.demo.dto.RegisterHomeDTO;
import com.spring.andre.demo.model.RegisterHome;
import com.spring.andre.demo.service.RegisterHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterHomeController {

    @Autowired
    private RegisterHomeService registerHomeService;
	
	@PostMapping(value = "/register/home")
    public RegisterHome registerHome(@RequestBody RegisterHomeDTO registerhomeDTO){
	    return registerHomeService.registerHome(registerhomeDTO);
    }
}
