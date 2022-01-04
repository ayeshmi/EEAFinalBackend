package com.example.demo.controller.webController;

import java.util.ArrayList;
import java.util.List;



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

	@PostMapping("/signinW")
	public ModelAndView Login(@ModelAttribute LoginRequest loginRequest) {
		
		JwtResponse jwtResponse = userService.loginService(loginRequest);
		ModelAndView modelAndView = new ModelAndView();
		if(jwtResponse != null) {
			String page=homeController.directUserToHomePage(jwtResponse);
			
			List<JwtResponse> lectures = new ArrayList<>();
			lectures.add(jwtResponse);
			modelAndView.setViewName(page);	
			modelAndView.addObject("user",lectures );
		}
		
		else {
			MessageResponse message=new MessageResponse("Invalid username and password, Check again.");
			System.out.println("hello wrong");
			modelAndView.setViewName("login");	
			modelAndView.addObject("error",message);
		}
		
		
		return modelAndView;
		
	}
	
	
	@PostMapping("/updateUser")
	public ModelAndView Update(@RequestParam("address") String address,@RequestParam("email") String email,
			@RequestParam("birthday") String birthday,@RequestParam("image") MultipartFile file,@RequestParam("username") String username) {
		      
		MessageResponse message=userService.updateProfile(email,address,birthday,file);
		ModelAndView modelAndView = new ModelAndView();
		
		if(message != null) {
			User user=userService.viewUserByEmail(email);
			modelAndView.addObject("user",user);
			modelAndView.addObject("ErrorMessage",message);
			modelAndView.setViewName("UpdateProfile");
		}
		else {
			modelAndView.addObject("ErrorMessage","Update is failed.");
			modelAndView.setViewName("UpdateProfile");	
		}
		
		//homeController.directUserToHomePage(jwtResponse);
		return modelAndView;
		
	}
	
	@GetMapping("/viewAllUserPage")
	public ModelAndView getAllUser() {
		// System.out.println("get item details"+file);
		
		List<User> users = userService.getAllUsers();
		ModelAndView modelAndView = new ModelAndView();
		
		if(users != null) {
			modelAndView.addObject("Users", users);
			modelAndView.setViewName("ViewAllUserTable");	
		}
		else {
			MessageResponse message=new MessageResponse("User list is empty.");
			modelAndView.addObject("ErrorMessage",message);
			modelAndView.setViewName("ViewAllUserTable");
		}

		

		return modelAndView;

	} 
	
	
	
	@PostMapping("/register")
	public ModelAndView registerUser(@ModelAttribute SignupRequest signUpRequest) {
		MessageResponse message= userService.registerUser(signUpRequest);
		
		ModelAndView modelAndView = new ModelAndView();
		if(message != null) {
			String messageWeb=message.getMessage();
			if(messageWeb.equals("User registered successfully!")) {
				modelAndView.addObject("error",message);
				modelAndView.setViewName("login");	
			}else {
				modelAndView.setViewName("signup");	
				modelAndView.addObject("loginError",message);
			}
		}
		else {
			System.out.println("Error is calling");
			modelAndView.setViewName("signup");	
			MessageResponse messageError=new MessageResponse("Incorrect email format!");
			modelAndView.addObject("loginError",messageError);
		}
	return modelAndView;
		
	}
	
	@GetMapping("/viewUserByID/{id}")
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
		       modelAndView.addObject("success",message);
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
	public ModelAndView advanceItemSearch(@RequestParam("search") String search) {
		// System.out.println("get item details"+file);
		System.out.println("Called12345wwww"+search);
		List<User> users = userService.advanceItemSearch(search);
		System.out.println("xxxxxxxx"+users.size());
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("Users", users);
		modelAndView.setViewName("ViewAllUserTable");
		

		return modelAndView;

	}
	
	@RequestMapping("/home")
	public String Hello() {
		return "Home";
	}
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "Register";
	}
	@RequestMapping("/register")
	public String home() {
		return "signup";
	}
	@RequestMapping("/login")
	public String home12() {
		return "login";
	}
	@RequestMapping("/contactUs")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String contactUspageLoggegUSer() {
		return "ContactUs";
	}
	
	@RequestMapping("/AllContactUs")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String contactUspageAllUser() {
		return "AllUserContactUs";
	}
	@RequestMapping("/profile")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String profilePage() {
		return "ViewProfile";
	}
	
	@RequestMapping("/updateProfile")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String UpdateProfilePage() {
		return "UpdateProfile";
	}
	
	@RequestMapping("/attendence")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String attenence() {
		return "Attendence";
	}
	
	@RequestMapping("/deleteUserPage")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String DeleteUserPage() {
		return "DeleteUser";
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "AboutUs";
	}
	
	@RequestMapping("/pharmacistHomePage")
	public String pharmacistHomePage() {
		return "PharmacientHomePage";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/1")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String home123oo() {
		return "Hello";
	}
	
	
}
