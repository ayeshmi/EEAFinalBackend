package com.example.demo.controller.webController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.model.Pharmacient;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.service.PharmacientServiceImpl;
import com.example.demo.service.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class PharmacientController {

	@Autowired
	private PharmacientServiceImpl pharmacientService;

	@Autowired
	private UserServiceImpl userService;


	@PostMapping("/addPharmacient")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView addPharmacient(@RequestParam("image") MultipartFile file, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("role") String role) {
		
		MessageResponse msg=pharmacientService.addPharmacient(file, firstName, lastName, contactNumber, email, address);
		ModelAndView modelAndView = new ModelAndView();
		if(msg !=null) {
			SignupRequest request = new SignupRequest();
			request.setUsername(firstName);
			request.setEmail(email);
			request.setPassword(firstName);
			Set<String> strRoles = new HashSet<String>();
			strRoles.add(role);
			request.setRole(strRoles);
			userService.registerUser(request);
			modelAndView.setViewName("AddPharmacient");	
			modelAndView.addObject("ErrorMessage", msg);
		}
		else {
			MessageResponse message=new MessageResponse("Someting went wrong, try again");
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.setViewName("AddPharmacient");		
		}

		 return modelAndView;

	}

	@PostMapping("/addAttendence")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView addAttendence(@RequestParam("email") String email, @RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, @RequestParam("username") String username,
			@RequestParam("id") Long id) {

		MessageResponse message = pharmacientService.addAttendence(email, startTime, endTime, username, id);
		ModelAndView modelAndView = new ModelAndView();
		if (message != null) {
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.setViewName("Attendence");
		} else {
			MessageResponse messageError = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("ErrorMessage", messageError);
			modelAndView.setViewName("Attendence");
		}

		return modelAndView;
	}

	@GetMapping("/viewAllPharmacient")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView getAllPharmacients() {
		
		List<Pharmacient> pharmacients = pharmacientService.getAllPharmacient();
		ModelAndView modelAndView = new ModelAndView();

		if(pharmacients.size() !=0) {
			modelAndView.addObject("pharmacients", pharmacients);
			modelAndView.setViewName("ViewAllPharmacient");
		}else {
			MessageResponse response =new MessageResponse("No records found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("AssignOrders");
		}
		
		return modelAndView;

	}

	@GetMapping("/viewPharmacientByItem/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView viewPharmacientByID(@PathVariable("id") Long id) {
		
		Pharmacient pharmacient = pharmacientService.viewItemByID(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("pharmacient", pharmacient);
		modelAndView.setViewName("viewSelectedPharmacientDetail");

		return modelAndView;

	}

	@GetMapping("/deletePharmacient")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView deletePharmacient(@RequestParam("userId") Long id) {
		MessageResponse message = pharmacientService.deletePharmacient(id);
		List<Pharmacient> pharmacients = pharmacientService.getAllPharmacient();
		ModelAndView modelAndView = new ModelAndView();
		if(message != null) {
			
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.addObject("pharmacients", pharmacients);
			modelAndView.setViewName("ViewAllPharmacient");
		}
		else {
			MessageResponse messageError = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("pharmacients", pharmacients);
			modelAndView.addObject("ErrorMessage", messageError);
			modelAndView.setViewName("ViewAllPharmacient");
		}

           return modelAndView;
	}


	
	@GetMapping("/viewPharmacientOrders/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView viewOrdersPharmacist(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orders=pharmacientService.viewOrdersPharmacist(id);
		
		if(orders.size()!=0) {
			modelAndView.addObject("items",orders);
			modelAndView.setViewName("ViewOrdersPharmacist");
		}
		else {
			MessageResponse response =new MessageResponse("No records found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewOrdersPharmacist");
		}
	
		return modelAndView;
			
	}
	
	
	@GetMapping("/advancePharmacistSearch")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView advancePharmacistSearch(@RequestParam("search") String search) {
		ModelAndView modelAndView = new ModelAndView();
		List<User> users = pharmacientService.advancePharmacistSearch(search);
		
		if(users.size()!=0) {
			modelAndView.addObject("pharmacients", users);
			modelAndView.setViewName("ViewAllPharmacient");
		}
		else {
			MessageResponse response =new MessageResponse("No records found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewAllPharmacient");
		}

		return modelAndView;

	}
	

}
