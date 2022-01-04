package com.example.demo.controller.apiController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/contactusRA/{Id}")
	public ResponseEntity<Object> addNewContactusDetails(@Valid @RequestBody ContactUs contactus,@PathVariable("Id") Long id) {
		MessageResponse message = null;
		message= contactusService.addNewContactusDetailswithUser(contactus,id);
		
		if(message != null) {
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		else {
			MessageResponse messageError = new MessageResponse("Someting went wrong, try again.");
			return new ResponseEntity<>(messageError,HttpStatus.OK);
		}
		
	}
	
	
	@GetMapping("/allConatctUsRA")
	public ResponseEntity<Object> getAllContactUsDetails(){
		List<ContactUs> list=contactusService.getAllContactUsDetails();
		if(list.size() != 0) {
			
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		else {
			System.out.println("list is empty");
			return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@GetMapping("/allUserConatctUsPerUserRA/{Id}")
	public ResponseEntity<Object> getAllContactUsDetailsPerUser(@PathVariable("Id") Long id){
		List<ContactUs> list=contactusService.viewContactUsForUser(id);
          if(list.size() != 0) {
			
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		else {
			System.out.println("list is empty");
			return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
		}

		
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
	//ContactUs updateConatctUS=contactusService.upadateContactUSDetails(id,contactUs.getAnswer(),contactUs.getEmail() );
		return null;
	}

}
