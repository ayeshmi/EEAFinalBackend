package com.example.demo.controller.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class FrontController {
	
	//Dispatch to main home page
	@RequestMapping("/home")
	public String Hello() {
		return "Home";
	}
	
	//Dispatch to register page
	@RequestMapping("/register")
	public String home() {
		return "signup";
	}
	
	//Dispatch to login page
	@RequestMapping("/login")
	public String login() {
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
}
