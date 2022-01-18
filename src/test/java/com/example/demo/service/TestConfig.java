package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controller.apiController.UserControllerAPI;
import com.example.demo.controller.webController.UserController;


@Configuration
public class TestConfig {
	@Autowired
	UserController userController;
	

	
	
	
	
	
}
