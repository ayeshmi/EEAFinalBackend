package com.example.demo.controller.apiController;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.security.*;



import com.example.demo.repository.UserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.model.*;
import com.example.demo.service.*;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ContactUsRepository contactusRepository;
	

	@Autowired
	EmailSender emailSender;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = userService.loginServiceRestApi(loginRequest);
		if (jwtResponse != null) {
			return ResponseEntity.ok(jwtResponse);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
	}
	@GetMapping("/employees12")
	public List<User> getAllEmployee(){
		return userRepository.findAll();
	}


	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		userService.registerUser(signUpRequest);
		ResponseEntity<?> message=userService.registerUser(signUpRequest);
		return ResponseEntity.ok(new MessageResponse(""+message));
		
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);

		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
	@GetMapping("/viewUserByIDRA/{id}")
	public User viewUserByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		User user = userService.viewItemByID(id);
		
		return user;

	}
	
	@GetMapping("/viewUserByEmailRA/{email}")
	public User viewUserByID(@PathVariable("email") String email) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		User user = userService.viewItemByEmail(email);
		
		return user;

	}
	

	
	

		
		
		

	
}
