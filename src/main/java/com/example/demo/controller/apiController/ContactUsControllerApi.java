package com.example.demo.controller.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContactUs;
import com.example.demo.model.MessageResponse;
import com.example.demo.service.ContactUsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ContactUsControllerApi {
	@Autowired
	ContactUsService contactusService;
	
	@PostMapping("/contactusRA")
	
	public ResponseEntity<?> addNewContactusDetails(@ModelAttribute ContactUs contactus) {
		
		 contactusService.addNewContactusDetails(contactus);
		return ResponseEntity.ok(new MessageResponse("Contact Details Succesfully Sent!"));
	}

}
