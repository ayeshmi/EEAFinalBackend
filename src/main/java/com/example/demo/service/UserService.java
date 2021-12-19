package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtResponse;

public interface UserService {
	public JwtResponse loginService(LoginRequest loginRequest);
	public JwtResponse loginServiceRestApi(LoginRequest loginRequest);
	public List<User> getAllUsers() ;
	public MessageResponse registerUser(SignupRequest signUpRequest);
	public  MessageResponse updateProfile(String email,String address,String birthday,MultipartFile file);
	public String imageUploader(MultipartFile file);
	public void deleteUser(Long id);
	public User viewUserByID(Long id);
	public User viewUserByEmail(String email);
}
