package com.example.demo.controller.webController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.service.ContactUsServiceImpl;

@Controller
@RequestMapping("/api/auth")
public class ContactUsController {

	@Autowired
	ContactUsRepository contactusRepository;

	@Autowired
	ContactUsServiceImpl contactusService;

	//view all comments
	@GetMapping("/allConatctUs")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView getAllContactUsDetails() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<ContactUs> list = contactusService.getAllContactUsDetails();
		
		if (list.size() != 0) {
			modelAndView.addObject("list", list);
			modelAndView.setViewName("ViewAllContactUs");
		} else {
			MessageResponse messageResponse = new MessageResponse("No Records.");
			modelAndView.addObject("ErrorMessage", messageResponse);
			modelAndView.setViewName("ViewAllContactUs");
		}

		return modelAndView;
	}

	//view comments for a particular user
	@GetMapping("/allUserConatctUsPerUser/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView getAllContactUsDetailsPerUser(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<ContactUs> list = contactusService.viewContactUsForUser(id);

		if (list.size() != 0) {
			modelAndView.addObject("list", list);
			modelAndView.setViewName("ViewAllContactUsPerUser");
		} else {
			MessageResponse messageResponse = new MessageResponse("No Records found!");
			modelAndView.addObject("ErrorMessage", messageResponse);
			modelAndView.setViewName("ViewAllContactUsPerUser");
		}

		return modelAndView;

	}

	// add new contact us request
	@PostMapping("/contactusW/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView addNewContactusDetails(@Valid@ModelAttribute ContactUs contactus, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		
		MessageResponse message = contactusService.addNewContactusDetailswithUser(contactus, id);
		
		if (message != null) {
			modelAndView.addObject("contactError", message);
			modelAndView.setViewName("ContactUs");
		} else {
			MessageResponse messageResponse = new MessageResponse("No records found!");
			modelAndView.addObject("contactError", messageResponse);
			modelAndView.setViewName("ContactUs");
		}

		return modelAndView;
	}
	
    //add new contact us request for unregistered user
	@PostMapping("/CommoncContactus")
	public ModelAndView addNewContactusDetailsAll(@ModelAttribute ContactUs contactus) {
		ModelAndView modelAndView = new ModelAndView();
		
		MessageResponse message = contactusService.addNewContactusDetails(contactus);
		
		if (message != null) {
			modelAndView.addObject("contactError", message);
			modelAndView.setViewName("AllUserContactUs");
		} else {
			MessageResponse messageResponse = new MessageResponse("No records found!");
			modelAndView.addObject("contactError", messageResponse);
			modelAndView.setViewName("AllUserContactUs");
		}

		return modelAndView;
	}

	//view selected contact us detail
	@GetMapping("/contactus/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView getContactUsDetailsById(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		ContactUs contactUs = contactusService.getContactUsDetailsById(id);
		 if(contactUs != null) {
			 modelAndView.addObject("contactUs", contactUs);
		     modelAndView.setViewName("ReplyContactUs"); 
		 }
		 else {
			 MessageResponse response = new MessageResponse("No records found!");
			 modelAndView.addObject("errorMsg", response);
			 modelAndView.setViewName("ViewCart");
		 }
		

		return modelAndView;
	}

	
    //reply contact us request
	@PostMapping("/contactus/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView replyContactUs(@PathVariable Long id, @ModelAttribute ContactUs contactUs) {
		ModelAndView modelAndView = new ModelAndView();
		
		MessageResponse response = contactusService.upadateContactUSDetails(id, contactUs.getAnswer(),
				contactUs.getEmail());
		
		List<ContactUs> list = contactusService.getAllContactUsDetails();
		
		if (response != null) {
			modelAndView.addObject("list", list);
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.addObject("contactUs", contactUs);
			modelAndView.setViewName("ViewAllContactUs");
		} else {
			modelAndView.addObject("list", list);
			MessageResponse responses = new MessageResponse("No records found!");
			modelAndView.addObject("ErrorMessage", responses);
			modelAndView.addObject("contactUs", contactUs);
			modelAndView.setViewName("ViewAllContactUs");
		}

		return modelAndView;
	}

	//delete contact us request from list
	@GetMapping("/deleteContactUs")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView deleteContactUs(@RequestParam("itemId") Long id) {
		
		MessageResponse response = contactusService.deleteContactUs(id);
		
		List<ContactUs> list = contactusService.getAllContactUsDetails();
		
		ModelAndView modelAndView = getAllContactUsDetails();
		
		if (response != null) {
			modelAndView.addObject("list", list);
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewAllContactUs");
		}
		else {
			MessageResponse responses = new MessageResponse("No records found!");
			modelAndView.addObject("list", list);
			modelAndView.addObject("ErrorMessage", responses);
			modelAndView.setViewName("ViewAllContactUs");
		}

		return modelAndView;

	}

}
