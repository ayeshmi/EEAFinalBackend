package com.example.demo.controller.apiController;


import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.security.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.Userdto;
import com.example.demo.model.*;
import com.example.demo.service.*;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserControllerAPI {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserServiceImpl userService;

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
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = userService.loginServiceRestApi(loginRequest);
		if (jwtResponse != null) {
			return ResponseEntity.ok(jwtResponse);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Check username and password"));
		}
	}
	@GetMapping("/employees12")
	public List<User> getAllEmployee(){
		return userRepository.findAll();
	}


	
	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@RequestBody SignupRequest signUpRequest) {
		MessageResponse message = null;
		message= userService.registerUser(signUpRequest);
		if(message != null) {
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		else {
			MessageResponse messageError = new MessageResponse("Incorrect format for email, try again.");
			return new ResponseEntity<>(messageError,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		MessageResponse message = null;
		userService.deleteUser(id);

		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
	@GetMapping("/viewUserByIDRA/{id}")
	public ResponseEntity<Object> viewUserByID(@PathVariable("id") Long id) {
	
		User user = userService.viewUserByID(id);
		if(user!= null) {
			return new ResponseEntity<>(user,HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
		}
	

	}
	
	@GetMapping("/viewUserByEmailRA/{email}")
	public ResponseEntity<Object> viewUserByID(@PathVariable("email") String email) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		User user = userService.viewUserByEmail(email);
		if(user!= null) {
			return new ResponseEntity<>(user,HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping("/updateUserRA")
	public ResponseEntity<Object> Update(@RequestBody Userdto user) {
		
		MessageResponse message=userService.updateProfileRA(user.getEmail(),user.getAddress(),user.getBirthday());
		
		
		if(message != null) {
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		else {
			MessageResponse messageError = new MessageResponse("Check inputs and try again.");
			return new ResponseEntity<>(messageError,HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	

		
		
		

	
}
