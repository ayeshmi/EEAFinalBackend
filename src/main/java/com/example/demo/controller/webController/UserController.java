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
	
	@GetMapping("/deleteUser")
	public ModelAndView deleteItem(@RequestParam("userId") Long id) {
		userService.deleteUser(id);
		List<User> users = userService.getAllUsers();
		ModelAndView modelAndView = new ModelAndView();
		
       MessageResponse message=new MessageResponse("hello ayeshmi");
       //modelAndView = getLectures(lecture.getScheduledDate());
       modelAndView.addObject("success",message);
       

		modelAndView.addObject("Users", users);
		
		modelAndView.setViewName("ViewAllUserTable");
     
	   
       return  modelAndView;

		
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
	
	@RequestMapping("/deleteUserPage")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String DeleteUserPage() {
		return "DeleteUser";
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "AboutUs";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/1")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	public String home123oo() {
		return "Hello";
	}
	
	
}
