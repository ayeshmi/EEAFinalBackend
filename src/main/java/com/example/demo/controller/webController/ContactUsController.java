package com.example.demo.controller.webController;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.ContactUs;
import com.example.demo.model.MessageResponse;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.service.ContactUsService;

@Controller
@RequestMapping("/api/auth")
public class ContactUsController {
	
	@Autowired
	ContactUsRepository contactusRepository;
	
	@Autowired
	ContactUsService contactusService;
	
	@GetMapping("/allConatctUs")
	public ModelAndView getAllContactUsDetails(){
		List<ContactUs> list=contactusService.getAllContactUsDetails();
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("list", list);
		modelAndView.setViewName("ViewAllContactUs");

		return modelAndView;
	}
	

	//create contactus details rest api
	@PostMapping("/contactusW")
	
	public ResponseEntity<?> addNewContactusDetails(@ModelAttribute ContactUs contactus) {
		
		 contactusService.addNewContactusDetails(contactus);
		return ResponseEntity.ok(new MessageResponse("Contact Details Succesfully Sent!"));
	}
	
	@GetMapping("/contactus/{id}")
	public ModelAndView getContactUsDetailsById(@PathVariable String id){
		
		ContactUs contactUs=contactusService.getContactUsDetailsById(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("contactUs", contactUs);
		modelAndView.setViewName("ReplyContactUs");

		return modelAndView;
	} 
	
	
	//update employee rest api
	
	@PostMapping("/contactus/{id}")
	public ResponseEntity<ContactUs> replyContactUs(@PathVariable Long id,@ModelAttribute ContactUs contactUs){
		System.out.println("sdsd");
	ContactUs updateConatctUS=contactusService.upadateContactUSDetails(id,contactUs.getAnswer(),contactUs.getEmail() );
		return ResponseEntity.ok(updateConatctUS);
	}
	
	@GetMapping("/deleteContactUs/{id}")
	public ResponseEntity<?> deleteContactUs(@PathVariable Long id){
		contactusService.deleteContactUs(id);
		
		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));
		
	}
	
}
