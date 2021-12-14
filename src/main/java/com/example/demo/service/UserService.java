package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.ERole;
import com.example.demo.model.EmailSender;
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
	
	@Autowired
	private FileStorageService fileStorageService;

	public JwtResponse loginService(LoginRequest loginRequest) {
		JwtResponse jwtResponse = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			System.out.println("Hello");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
					roles);

		} catch (Exception e) {
			System.out.println("error is " + e);
		}
		return jwtResponse;

	}

	public JwtResponse loginServiceRestApi(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getPassword(), loginRequest.getUsername()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles);

		return jwtResponse;
	}

	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			users = userRepository.findAll();
		} catch (Exception e) {
			System.out.println("error is " + e);
		}

		return users;
	}

	

	public MessageResponse registerUser(SignupRequest signUpRequest) {
		System.out.println("hello" + signUpRequest.getUsername());
		System.out.println("hello" + signUpRequest.getPassword());
		MessageResponse message = null;
		try {
			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				message = new MessageResponse("Error: Email is already in use!");

				return message;
			}

			// Create new user's account
			User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword()));

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
					case "pharmacist":
						Role modRole = roleRepository.findByName(ERole.ROLE_PHARMACIST)
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
			// emailSender=new EmailSender();
			emailSender.sendEmail();
			message = new MessageResponse("User registered successfully!");
		} catch (Exception e) {
			System.out.println("error is " + e);
		}

		return message;

	}

	public  MessageResponse updateProfile(String email,String address,String birthday,MultipartFile file) {
		
		MessageResponse message = null;
		try {
			User userU = userRepository.findByEmail(email);
			String imagePath=imageUploader(file);
			String fileName = fileStorageService.storeFile(file);
			userU.setImageName(fileName);
			userU.setImage(imagePath);
			
			userU.setBirthday(birthday);

			userU.setAddress(address);
			userRepository.save(userU);
			message=new MessageResponse("User details are successfully updated.");
		}
		catch(Exception e) {
			System.out.println("error is " + e);
		}
		
		return message;

	}
	
	public String imageUploader(MultipartFile file) {
		String fileDownloadUri=null;
		try {
			String fileName = fileStorageService.storeFile(file);
			 fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/auth/video/")
					.path(fileName)
					.toUriString();
		}catch(Exception e) {
			System.out.println("error is " + e);
		}
		
		
		return fileDownloadUri;
	}

	public void deleteUser(Long id) {
		try {
			User item = userRepository.findById(id).orElseThrow();
			userRepository.delete(item);
			System.out.println("user is deleted");
		} catch (Exception e) {
			System.out.println("error is " + e);
		}

	}

	public User viewItemByID(Long id) {
		User user=null;
		try {
			 user = userRepository.findById(id).orElseThrow();
		}catch(Exception e) {
			System.out.println("error is " + e);
		}
		
		return user;
	}

	public User viewItemByEmail(String email) {
		User user=null;
		try {
			 user = userRepository.findByEmail(email);
		}catch(Exception e) {
			System.out.println("error is " + e);
		}
		
		return user;
	}

}
