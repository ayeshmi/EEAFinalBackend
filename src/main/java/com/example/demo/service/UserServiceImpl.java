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

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.ERole;
import com.example.demo.model.EmailSender;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Role;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.JwtUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.repository.RoleRepository;

@Service
public class UserServiceImpl implements UserService{
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
	private FileStorageServiceImpl fileStorageService;

	@Override
	public JwtResponse loginService(LoginRequest loginRequest) {
		JwtResponse jwtResponse = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
            
			jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
					roles,userDetails.getProfileImage());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jwtResponse;

	}

	@Override
	public JwtResponse loginServiceRestApi(LoginRequest loginRequest) {
		JwtResponse jwtResponse = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getPassword(), loginRequest.getUsername()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles,userDetails.getProfileImage());
		}catch(Exception e) {
			e.printStackTrace();
		}

		

		return jwtResponse;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			users = userRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	
	@Override
	public MessageResponse registerUser(SignupRequest signUpRequest) {
		
		MessageResponse message = null;
		try {
			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				message = new MessageResponse("Error: Email is already in use!");

				return message;
			}
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				message = new MessageResponse("Error: Username is already in use!");

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
			e.printStackTrace();
		}

		return message;

	}

	@Override
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
			e.printStackTrace();
		}
		
		return message;

	}
	
	@Override
	public String imageUploader(MultipartFile file) {
		String fileDownloadUri=null;
		try {
			String fileName = fileStorageService.storeFile(file);
			 fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/auth/video/")
					.path(fileName)
					.toUriString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return fileDownloadUri;
	}

	@Override
	public void deleteUser(Long id) {
		try {
			User item = userRepository.findById(id).orElseThrow();
			System.out.println("user is deletedss");
			userRepository.foreigKeyProblem();
			System.out.println("user is deletedss");
			userRepository.delete(item);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public User viewUserByID(Long id) {
		User user=null;
		try {
			 user = userRepository.findById(id).orElseThrow();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User viewUserByEmail(String email) {
		User user=null;
		try {
			 user = userRepository.findByEmail(email);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public MessageResponse updateProfileRA(String email, String address, String birthday) {
		MessageResponse message = null;
		try {
			User userU = userRepository.findByEmail(email);
			
			userU.setBirthday(birthday);

			userU.setAddress(address);
			userRepository.save(userU);
			message=new MessageResponse("User details are successfully updated.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}

	public List<User> advanceItemSearch(String search) {
		List<User> users=null;
		try {
			users=userRepository.search(search);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}
