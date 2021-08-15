package com.spring.andre.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;
import java.util.Scanner;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
	    SpringApplication.run(DemoApplication.class, args);
	}	
}
