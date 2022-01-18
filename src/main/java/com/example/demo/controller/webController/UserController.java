package com.example.demo.controller.webController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.*;
import com.example.demo.security.*;
import com.example.demo.service.*;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private HomePageControllerFacade homeController;

	//login function of the all users
	@PostMapping("/signinW")
	public ModelAndView Login(@Valid @ModelAttribute LoginRequest loginRequest) {
		ModelAndView modelAndView = new ModelAndView();
		
		JwtResponse jwtResponse = userService.loginService(loginRequest);
		
		if(jwtResponse != null) {
			String page=homeController.directUserToHomePage(jwtResponse);		
			List<JwtResponse> loginResponse = new ArrayList<>();
			loginResponse.add(jwtResponse);  
			modelAndView.setViewName(page);	
			modelAndView.addObject("user",loginResponse);
		}	
		else {
			MessageResponse message=new MessageResponse("Invalid username or password, Try again.");
			modelAndView.setViewName("login");	
			modelAndView.addObject("error",message);
		}
	
		return modelAndView;	
	}
	
	
	@PostMapping("/updateUser")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView Update(@RequestParam("address") String address,@RequestParam("email") String email,
			@RequestParam("birthday") String birthday,@RequestParam("image") MultipartFile file,@RequestParam("username") String username) {
		      
		MessageResponse message=userService.updateProfile(email,address,birthday,file);
		ModelAndView modelAndView = new ModelAndView();
		
		if(message != null) {
			User user=userService.viewUserByEmail(email);
			modelAndView.addObject("user",user);
			modelAndView.addObject("success",message);
			modelAndView.setViewName("UpdateProfile");
		}
		else {
			modelAndView.addObject("error","Update is failed.");
			modelAndView.setViewName("UpdateProfile");	
		}
		
		return modelAndView;
		
	}
	
	@GetMapping("/viewAllUserPage")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView getAllUser() {
		
		List<User> users = userService.getAllUsers();
		ModelAndView modelAndView = new ModelAndView();
		
		if(users != null) {
			modelAndView.addObject("Users", users);
			modelAndView.setViewName("ViewAllUserTable");	
		}
		else {
			MessageResponse message=new MessageResponse("User list is empty.");
			modelAndView.addObject("error",message);
			modelAndView.setViewName("ViewAllUserTable");
		}

		return modelAndView;

	} 
	
	
	
	@PostMapping("/register")
	public ModelAndView registerUser( @ModelAttribute SignupRequest signUpRequest) {
		MessageResponse message= userService.registerUser(signUpRequest);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(message != null) {
			String messageWeb=message.getMessage();
			if(messageWeb.equals("User successfully registered!")) {
				modelAndView.addObject("success",message);
				modelAndView.setViewName("login");	
			}else {
				modelAndView.setViewName("signup");	
				modelAndView.addObject("error",message);
			}
		}
		else {
			modelAndView.setViewName("signup");	
			MessageResponse messageError=new MessageResponse("Incorrect email format!");
			modelAndView.addObject("error",messageError);
		}
	return modelAndView;
		
	}
	
	@GetMapping("/viewUserByID/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView viewUserByID(@PathVariable("id") Long id) {
		
		User user = userService.viewUserByID(id);
		ModelAndView modelAndView = new ModelAndView();
		if(user != null) {
			modelAndView.addObject("user", user);
			modelAndView.setViewName("ViewProfile");
	
		}
		else {
			MessageResponse message=new MessageResponse("User is not valid");
			modelAndView.addObject("ErrorMessage",message);
			modelAndView.setViewName("ViewAllUserTable");
		}
	return modelAndView;

	}
	
	@GetMapping("/viewUserUpdate/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView viewUserByIDUpdate(@PathVariable("id") Long id) {
		User user = userService.viewUserByID(id);
		ModelAndView modelAndView = new ModelAndView();
		if(user != null) {
			modelAndView.addObject("user", user);
			modelAndView.setViewName("UpdateProfile");
	
		}
		else {
			MessageResponse message=new MessageResponse("User is not valid");
			modelAndView.addObject("ErrorMessage",message);
			modelAndView.setViewName("ViewAllUserTable");
		}
	
		return modelAndView;

	}
	
	@GetMapping("/deleteUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView deleteUser(@RequestParam("userId") Long id) {
		userService.deleteUser(id);
		List<User> users = userService.getAllUsers();
		ModelAndView modelAndView = new ModelAndView();
		if(users != null) {
			 MessageResponse message=new MessageResponse("User is successfully deleted.");
		       //modelAndView = getLectures(lecture.getScheduledDate());
		       modelAndView.addObject("ErrorMessage",message);
			   modelAndView.addObject("Users", users);
		}
		else {
			MessageResponse message=new MessageResponse("Something went wrong, try again!");
		     
		       modelAndView.addObject("success",message);
		}
	
		modelAndView.setViewName("ViewAllUserTable");
   
       return  modelAndView;
	}
	
	@GetMapping("/advanceUserSearch")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView advanceItemSearch(@RequestParam("search") String search) {
	
		List<User> users = userService.advanceItemSearch(search);
		ModelAndView modelAndView = new ModelAndView();
       if(users.size()!=0) {
    	modelAndView.addObject("Users", users);
   		 
       }
       else {
    	   MessageResponse message=new MessageResponse("No machers found!");
    	   modelAndView.addObject("errorMsg",message);
    	   
       }
		
       modelAndView.setViewName("ViewAllUserTable");
	   return modelAndView;

	}
	
	

	
	
}
