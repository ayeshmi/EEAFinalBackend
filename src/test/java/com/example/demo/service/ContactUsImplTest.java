package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;

@SpringBootTest
public class ContactUsImplTest {

	@Autowired
	ContactUsService contactUsService;

	@MockBean
	ContactUsRepository contactusRepository;

	@Test
	@DisplayName("Test findAll")
	void testFindAll() {
		// Setup our mock repository
		ContactUs widget1 = new ContactUs(1l, "Widget Name", "Description", "d");
		ContactUs widget2 = new ContactUs(2l, "Widget Name", "Description", "d");

		doReturn(Arrays.asList(widget1, widget2)).when(contactusRepository).findAllContactUs();

		// Execute the service call
		List<ContactUs> widgets = contactUsService.getAllContactUsDetails();

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}

	@Test
	@DisplayName("Test save")
	void testSave() {
		// Setup our mock repository
		ContactUs contactUS = new ContactUs(1l, "Widget Name", "Description", "d", "d");
		doReturn(contactUS).when(contactusRepository).save(any());

		// Execute the service call
		contactUsService.addNewContactusDetails(contactUS);

	}

	@Test
	@DisplayName("Test save getBy ID")
	void testGetContactUsDetailsById() {

		// Setup our mock repository
		ContactUs contactUS = new ContactUs(1l, "Widget Name", "Description", "d");
		contactusRepository.save(contactUS);
		ContactUs a = null;
		doReturn(a).when(contactusRepository).getOne(contactUS.getId());
		contactUsService.getContactUsDetailsByIdAPI(Long.valueOf(30));

	}

	@Test
	@DisplayName("Test save getBy ID")
	void testUpadateContactUSDetailsAPI() {

		// Setup our mock repository
		ContactUs contactUS = new ContactUs(1l, "Widget Name", "Description", "d");
		contactusRepository.save(contactUS);
		ContactUs a = null;
		doReturn(a).when(contactusRepository).getOne(contactUS.getId());
		contactUsService.getContactUsDetailsByIdAPI(Long.valueOf(30));

	}

	@Test
	@DisplayName("Test save getBy ID")
	void testUpadateContactUSDetails() {

		// Setup our mock repository
		ContactUs contactUS = new ContactUs(1l, "Widget Name", "Description", "d");
		contactusRepository.save(contactUS);
		ContactUs a = null;
		doReturn(a).when(contactusRepository).getOne(contactUS.getId());
		contactUsService.getContactUsDetailsById("30");

	}

	

	@Test
	@DisplayName("Test  deleteContactUs")
	void testDeleteContactUs() {
		Optional<ContactUs> contactUS = Optional.of(new ContactUs(1l, "Widget Name", "Description", "d", "d"));

		doReturn(contactUS).when(contactusRepository).findById(1l);

		MessageResponse msg = contactUsService.deleteContactUs(contactUS.get().getId());

		Assertions.assertEquals(msg.getMessage(), "Contact Us Detail is sucessfully deleted.");

	}

}
