package com.example.demo.service;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class UserImplTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	@DisplayName("Test getAllUsers")
	void testViewCartDetailsUser() {
		// Setup our mock repository
				User user1 = new User();
				User user2 = new User();

				doReturn(Arrays.asList(user1, user2)).when(userRepository).findAll();

				// Execute the service call
				List<User> widgets = userService.getAllUsers();

				// Assert the response
				Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}
	

	


}
