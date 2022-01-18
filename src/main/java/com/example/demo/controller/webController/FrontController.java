package com.example.demo.controller.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//implementation of front controller design pattern
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
	
	//Dispatch to unregistered contact us page
	@RequestMapping("/contactUs")
	public String contactUspageLoggegUSer() {
		return "ContactUs";
	}
	
	//Dispatch to all contact us page
	@RequestMapping("/AllContactUs")
	public String contactUspageAllUser() {
		return "AllUserContactUs";
	}
	
	//Dispatch to profile page
	@RequestMapping("/profile")
	public String profilePage() {
		return "ViewProfile";
	}
	
	//Dispatch to update profile page
	@RequestMapping("/updateProfile")
	public String UpdateProfilePage() {
		return "UpdateProfile";
	}
	
	//Dispatch to attendence page
	@RequestMapping("/attendence")
	public String attenence() {
		return "Attendence";
	}
	
	//Dispatch to delete user page
	@RequestMapping("/deleteUserPage")
	public String DeleteUserPage() {
		return "DeleteUser";
	}
	
	//Dispatch to about us page
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "AboutUs";
	}
	
	//Dispatch to pharamacist home page
	@RequestMapping("/pharmacistHomePage")
	public String pharmacistHomePage() {
		return "PharmacientHomePage";
	}
	
	//Dispatch to add order by prescription page
	@RequestMapping("/addOrderByPrescription")
	public String addOrderByPrescription() {
		return "AddOrderByMedical Prescription";
	}
	
	//Dispatch to view all categories page
	@RequestMapping("/itemCategorical")
	public String addItemCategoricall() {
		return "ViewItemCategorical";
	}
	
	@RequestMapping("/addItemPage")
	public String addItemPage() {
		return "AddItem";
	}
	
	@RequestMapping("/addPharmacientPage")
	public String addPharmacientPage() {
		return "AddPharmacient";
	}
}
