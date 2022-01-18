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
import com.example.demo.model.Pharmacient;
import com.example.demo.model.User;
import com.example.demo.repository.PharmacientRepository;

@SpringBootTest
public class PharmacientImplTest {
	@MockBean
	private PharmacientRepository pharmacientRepository;

	@Autowired
	private PharmacientService pharmacientService;

	@Test
	@DisplayName("Test getAllPharmacient")
	void testGetAllPharmacient() {
		// Setup our mock repository
		Pharmacient pharmacient1 = new Pharmacient();
		Pharmacient pharmacient2 = new Pharmacient();

		doReturn(Arrays.asList(pharmacient1, pharmacient2)).when(pharmacientRepository).findAll();

		// Execute the service call
		List<Pharmacient> widgets = pharmacientService.getAllPharmacient();

		// Assert the response
		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
	}

	@Test
	@DisplayName("Test viewItemByID")
	void testViewItemByID() {
		Optional<Pharmacient> pharmacient1 = Optional.of(new Pharmacient());

		doReturn(pharmacient1).when(pharmacientRepository).findById(pharmacient1.get().getId());

		Pharmacient pharmacient2 = pharmacientService.viewItemByID(pharmacient1.get().getId());

		Assertions.assertEquals(pharmacient2.getId(), pharmacient1.get().getId());
	}

	@Test
	@DisplayName("Test deletePharmacient")
	void testDeletePharmacient() {
		Optional<Pharmacient> pharmacient1 = Optional.of(new Pharmacient());

		doReturn(pharmacient1).when(pharmacientRepository).findById(pharmacient1.get().getId());

		MessageResponse msg = pharmacientService.deletePharmacient(pharmacient1.get().getId());

		Assertions.assertEquals(msg.getMessage(), "Pharamacist is successfully deleted");
	}

	@Test
	@DisplayName("Test save")
	void testSave() {
		// Setup our mock repository
		Pharmacient pharmacient1 = new Pharmacient();
		doReturn(pharmacient1).when(pharmacientRepository).save(any());

		// Execute the service call
		pharmacientService.addPharmacientAPI(pharmacient1);

	}

}
