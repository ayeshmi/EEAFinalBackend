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

	@GetMapping("/allConatctUs")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView getAllContactUsDetails() {
		List<ContactUs> list = contactusService.getAllContactUsDetails();
		ModelAndView modelAndView = new ModelAndView();
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

	@GetMapping("/allUserConatctUsPerUser/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView getAllContactUsDetailsPerUser(@PathVariable("id") Long id) {
		List<ContactUs> list = contactusService.viewContactUsForUser(id);

		ModelAndView modelAndView = new ModelAndView();
		if (list.size() != 0) {
			modelAndView.addObject("list", list);
			modelAndView.setViewName("ViewAllContactUsPerUser");
		} else {
			MessageResponse messageResponse = new MessageResponse("No Records.");
			modelAndView.addObject("ErrorMessage", messageResponse);
			modelAndView.setViewName("ViewAllContactUsPerUser");
		}

		return modelAndView;

	}

	// create contactus details rest api
	@PostMapping("/contactusW/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView addNewContactusDetails(@Valid@ModelAttribute ContactUs contactus, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		MessageResponse message = contactusService.addNewContactusDetailswithUser(contactus, id);
		if (message != null) {
			modelAndView.addObject("contactError", message);
			modelAndView.setViewName("ContactUs");
		} else {
			MessageResponse messageResponse = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("contactError", messageResponse);
			modelAndView.setViewName("ContactUs");
		}

		return modelAndView;
	}

	@PostMapping("/CommoncContactus")
	public ModelAndView addNewContactusDetailsAll(@ModelAttribute ContactUs contactus) {
		ModelAndView modelAndView = new ModelAndView();
		MessageResponse message = contactusService.addNewContactusDetails(contactus);
		if (message != null) {
			modelAndView.addObject("contactError", message);
			modelAndView.setViewName("AllUserContactUs");
		} else {
			MessageResponse messageResponse = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("contactError", messageResponse);
			modelAndView.setViewName("AllUserContactUs");
		}

		return modelAndView;
	}

	@GetMapping("/contactus/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView getContactUsDetailsById(@PathVariable String id) {

		ContactUs contactUs = contactusService.getContactUsDetailsById(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("contactUs", contactUs);
		modelAndView.setViewName("ReplyContactUs");

		return modelAndView;
	}

	// update employee rest api

	@PostMapping("/contactus/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView replyContactUs(@PathVariable Long id, @Valid@ModelAttribute ContactUs contactUs) {
		MessageResponse response = contactusService.upadateContactUSDetails(id, contactUs.getAnswer(),
				contactUs.getEmail());
		System.out.println("helooo buddy"+response);
		List<ContactUs> list = contactusService.getAllContactUsDetails();
		ModelAndView modelAndView = new ModelAndView();
		if (response != null) {
			modelAndView.addObject("list", list);
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.addObject("contactUs", contactUs);
			modelAndView.setViewName("ViewAllContactUs");
		} else {
			modelAndView.addObject("list", list);
			MessageResponse responses = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("ErrorMessage", responses);
			modelAndView.addObject("contactUs", contactUs);
			modelAndView.setViewName("ViewAllContactUs");
		}

		return modelAndView;
	}

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
			MessageResponse responses = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("list", list);
			modelAndView.addObject("ErrorMessage", responses);
			modelAndView.setViewName("ViewAllContactUs");
		}

		return modelAndView;

	}

}
