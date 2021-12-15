package com.example.demo.controller.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.ContactUs;
import com.example.demo.dto.MessageResponse;
import com.example.demo.service.ContactUsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ContactUsControllerApi {
	@Autowired
	ContactUsServiceImpl contactusService;
	
	@PostMapping("/contactusRA")
	public ResponseEntity<?> addNewContactusDetails(@ModelAttribute ContactUs contactus) {
		
		 contactusService.addNewContactusDetails(contactus);
		return ResponseEntity.ok(new MessageResponse("Contact Details Succesfully Sent!"));
	}
	
	
	@GetMapping("/allConatctUsRA")
	public List<ContactUs> getAllContactUsDetails(){
		List<ContactUs> list=contactusService.getAllContactUsDetails();
		

		return list;
	}
	
	@DeleteMapping("/deleteContactUsRA/{id}")
	public ResponseEntity<?> deleteContactUs(@PathVariable Long id){
		contactusService.deleteContactUs(id);
		
		
		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));
		
	}
	@GetMapping("/contactusDetailsRA/{id}")
	public ContactUs getContactUsDetailsById(@PathVariable("id") Long id){
		System.out.println("Hello method is called");
		
		ContactUs contactUs=contactusService.getContactUsDetailsByIdAPI(id);
		return contactUs;
	} 
	
	@PutMapping("/contactusReplyRA/{id}")
	public ResponseEntity<ContactUs> replyContactUs(@PathVariable Long id,@RequestBody ContactUs contactUs){
		System.out.println("sdsd");
	ContactUs updateConatctUS=contactusService.upadateContactUSDetails(id,contactUs.getAnswer(),contactUs.getEmail() );
		return ResponseEntity.ok(updateConatctUS);
	}

}
