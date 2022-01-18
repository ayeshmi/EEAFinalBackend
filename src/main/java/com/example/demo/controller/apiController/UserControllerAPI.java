package com.example.demo.controller.apiController;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

	// login
	@PostMapping("/signin")
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = userService.loginServiceRestApi(loginRequest);
		if (jwtResponse != null) {
			return ResponseEntity.ok(jwtResponse);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Invalid username and password, check again."));
		}
	}

	// register
	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@RequestBody SignupRequest signUpRequest) {
		MessageResponse message = null;
		message = userService.registerUser(signUpRequest);
		if (message != null) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			MessageResponse messageError = new MessageResponse("Incorrect format for email, try again.");
			return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
		}

	}

	// view selected user details by ID
	@GetMapping("/viewUserByIDRA/{id}")
	public ResponseEntity<Object> viewUserByID(@PathVariable("id") Long id) {

		User user = userService.viewUserByID(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}

	}

	// view all user details
	@GetMapping("/employees12")
	public ResponseEntity<Object> getAllEmployee() {
		List<User> users = userRepository.findAll();
		if (users.size() != 0) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(users, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewUserByEmailRA/{email}")
	public ResponseEntity<Object> viewUserByID(@PathVariable("email") String email) {

		System.out.println("Called1234");
		User user = userService.viewUserByEmail(email);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

		userService.deleteUser(id);

		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}

	// this method is used to update the user details.
	@PutMapping("/updateUserRA")
	public ResponseEntity<Object> Update(@RequestBody Userdto user) {

		MessageResponse message = userService.updateProfileRA(user.getEmail(), user.getAddress(), user.getBirthday());

		if (message != null) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			MessageResponse messageError = new MessageResponse("Check inputs and try again.");
			return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/advanceUserSearchAPI/{search}")
	public ResponseEntity<Object> advanceItemSearch(@PathVariable("search") String search) {

		List<User> users = userService.advanceItemSearch(search);
		if (users.size() != 0) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(users, HttpStatus.BAD_REQUEST);
		}

	}

}
