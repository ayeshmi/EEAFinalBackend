package com.example.demo.service;



import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.example.demo.model.ERole;
import com.example.demo.model.EmailSender;
import com.example.demo.model.Item;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.Role;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.model.UserDetailsImpl;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.JwtUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.repository.RoleRepository;

@Service
public class UserService {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EmailSender emailSender;
	
	public JwtResponse loginService(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
System.out.println("Hello");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		JwtResponse jwtResponse=new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles
				 );
		
		return jwtResponse;
	}
	
	public JwtResponse loginServiceRestApi(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getPassword(),loginRequest.getUsername()));
System.out.println("Hello");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		JwtResponse jwtResponse=new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles
				 );
		
		return jwtResponse;
	}
	
	public List<User> getAllUsers(){
		List<User> users=userRepository.findAll();
		return users;
	}

	public User uploadProfileDetails(User user1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
		System.out.println("hello"+signUpRequest.getUsername());
		System.out.println("hello"+signUpRequest.getPassword());
		
		

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
				User user = new User(signUpRequest.getUsername(), 
						 signUpRequest.getEmail(), 
						 encoder.encode(signUpRequest.getPassword())
						 );
				 
			
		//String username=signUpRequest.getUsername();
		
        
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
			
		}

		user.setRoles(roles);
		userRepository.save(user);
		//emailSender=new EmailSender();
		emailSender.sendEmail();
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		
	}

	public void updateProfile(User user) {
		User userU=userRepository.findByEmail(user.getEmail());
		userU.setUsername(user.getUsername());
		userU.setBirthday(user.getBirthday());
		userU.setAddress(user.getAddress());
		userRepository.save(userU);
		System.out.println("updated");
		
		
	}

	public void deleteUser(Long id) {
		User item=userRepository.findById(id)
				.orElseThrow();
		userRepository.delete(item);
		System.out.println("user is deleted");
		
		
	}

	public User viewItemByID(Long id) {
		User user=userRepository.findById(id).orElseThrow();
		return user;
	}

	public User viewItemByEmail(String email) {
		User user=userRepository.findByEmail(email);
		return user;
	}
	

	
	
}
