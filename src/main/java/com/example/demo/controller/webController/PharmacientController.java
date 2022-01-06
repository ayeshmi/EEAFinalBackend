package com.example.demo.controller.webController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView addPharmacient(@RequestParam("image") MultipartFile file, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("role") String role) {
		// System.out.println("get item details"+file);
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
	public ModelAndView getAllPharmacients() {
		// System.out.println("get item details"+file);
		List<Pharmacient> pharmacients = pharmacientService.getAllPharmacient();
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("pharmacients", pharmacients);
		modelAndView.setViewName("ViewAllPharmacient");

		return modelAndView;

	}

	@GetMapping("/viewPharmacientByItem/{id}")
	public ModelAndView viewPharmacientByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		Pharmacient pharmacient = pharmacientService.viewItemByID(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("pharmacient", pharmacient);
		modelAndView.setViewName("viewSelectedPharmacientDetail");

		return modelAndView;

	}

	@GetMapping("/deletePharmacient")
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

	@RequestMapping("/addPharmacientPage")
	public String addItemPage() {
		return "AddPharmacient";
	}
	
	@GetMapping("/viewPharmacientOrders/{id}")
	public ModelAndView viewOrdersPharmacist(@PathVariable("id") Long id){
		
		List<Order> orders=pharmacientService.viewOrdersPharmacist(id);

		System.out.println("orders sizeaaa"+orders.size());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("items",orders);
		modelAndView.setViewName("ViewOrdersPharmacist");
		
		
		
		return modelAndView;
		
		
	}
	
	@GetMapping("/advancePharmacistSearch")
	public ModelAndView advancePharmacistSearch(@RequestParam("search") String search) {
		// System.out.println("get item details"+file);
		System.out.println("Called12345wwww"+search);
		List<User> users = pharmacientService.advancePharmacistSearch(search);
		System.out.println("xxxxxxxx"+users.size());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pharmacients", users);
		modelAndView.setViewName("ViewAllPharmacient");

		return modelAndView;

	}
	

}
