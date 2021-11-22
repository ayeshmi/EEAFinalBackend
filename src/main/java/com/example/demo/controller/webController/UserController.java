package com.example.demo.controller.webController;




import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


import com.example.demo.model.*;
import com.example.demo.security.*;
import com.example.demo.service.*;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;




@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HomePageControllerFacade homeController;

	@PostMapping("/signinW")
	public ModelAndView Login(@ModelAttribute LoginRequest loginRequest) {
		JwtResponse jwtResponse = userService.loginService(loginRequest);
		String page=homeController.directUserToHomePage(jwtResponse);
		ModelAndView modelAndView = new ModelAndView();
		List<JwtResponse> lectures = new ArrayList<>();
		lectures.add(jwtResponse);
		if(page!=null) {
			modelAndView.setViewName(page);	
			modelAndView.addObject("user",lectures );
		}
		else {
			//modelAndView.addObject("Check Entered values again");
		}
		
		//homeController.directUserToHomePage(jwtResponse);
		return modelAndView;
		
	}
	
	
	@PostMapping("/updateUser")
	public ModelAndView Update(@ModelAttribute User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        System.out.println(username);
		//JwtResponse jwtResponse = userService.loginService(loginRequest);
		userService.updateProfile(user);
		ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("Home");	
		//homeController.directUserToHomePage(jwtResponse);
		return modelAndView;
		
	}
	
	 
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@ModelAttribute SignupRequest signUpRequest) {
		
		
		return ResponseEntity.ok(userService.registerUser(signUpRequest));
		
	}
	
 
	
	@RequestMapping("/home")
	public String Hello() {
		return "Home";
	}
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "Register";
	}
	@RequestMapping("/a")
	public String home() {
		return "signup";
	}
	@RequestMapping("/b")
	public String home12() {
		return "login";
	}
	@RequestMapping("/c")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String home123() {
		return "ContactUs";
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
	
	
	@ResponseBody
	@RequestMapping("/1")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String home123oo() {
		return "Hello";
	}
	
	
}
