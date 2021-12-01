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
		System.out.println("request is loaded"+loginRequest);
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
	
	@GetMapping("/viewAllUserPage")
	public ModelAndView getAllUser() {
		// System.out.println("get item details"+file);
		List<User> users = userService.getAllUsers();
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("Users", users);
		modelAndView.addObject("message", "dsdsjdsd");
		modelAndView.setViewName("ViewAllUserTable");

		return modelAndView;

	} 
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@ModelAttribute SignupRequest signUpRequest) {
		
		
		return ResponseEntity.ok(userService.registerUser(signUpRequest));
		
	}
	
	@GetMapping("/viewUserByID/{id}")
	public ModelAndView viewUserByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		User user = userService.viewItemByID(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("user", user);
		modelAndView.setViewName("ViewSelectedUserDetails");

		return modelAndView;

	}
	
	@GetMapping("/deleteUser/{id}")
	public void deleteItem(@PathVariable Long id) {
		userService.deleteUser(id);

		//return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

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
