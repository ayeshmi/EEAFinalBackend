package com.example.demo.controller.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Pharmacient;
import com.example.demo.model.User;
import com.example.demo.service.PharmacientServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class PharmacientControllerAPI {

	@Autowired
	private PharmacientServiceImpl pharmacientService;

	// view all pharmacist
	@GetMapping("/viewAllPharmacientRA")
	public ResponseEntity<Object> getAllPharmacients() {
		List<Pharmacient> pharmacients = pharmacientService.getAllPharmacient();
		if (pharmacients.size() != 0) {
			return new ResponseEntity<>(pharmacients, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(pharmacients, HttpStatus.BAD_REQUEST);
		}

	}

	// add pharmacist
	@PostMapping("/addPharmacientRA")
	public ResponseEntity<Object> addPharmacient(@RequestBody Pharmacient pharmacient) {
		pharmacientService.addPharmacientAPI(pharmacient);

		return ResponseEntity.badRequest().body(new MessageResponse("Item is added successfully"));

	}

	// view selected pharmcist details by ID
	@GetMapping("/viewPharmacientByItemRA/{id}")
	public ResponseEntity<Object> viewPharmacientByID(@PathVariable("id") Long id) {

		Pharmacient pharmacient = pharmacientService.viewItemByID(id);

		return new ResponseEntity<>(pharmacient, HttpStatus.OK);

	}

	@GetMapping("/advancePharmacistSearchAPI/{search}")
	public ResponseEntity<Object> advancePharmacistSearch(@PathVariable("search") String search) {

		List<User> users = pharmacientService.advancePharmacistSearch(search);
		if (users.size() != 0) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(users, HttpStatus.BAD_REQUEST);
		}

	}

}
