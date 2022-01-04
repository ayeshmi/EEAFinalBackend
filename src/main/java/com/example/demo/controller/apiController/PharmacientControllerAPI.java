package com.example.demo.controller.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Pharmacient;
import com.example.demo.service.PharmacientServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class PharmacientControllerAPI {
	
	@Autowired
	private PharmacientServiceImpl pharmacientService;

	
	@PostMapping("/addPharmacientRA")
	public ResponseEntity<?> addPharmacient(@RequestBody Pharmacient pharmacient ) {
		// System.out.println("get item details"+file);
		pharmacientService.addPharmacientAPI(pharmacient);

		return ResponseEntity.badRequest().body(new MessageResponse("Item is added successfully"));


	}
	
	@GetMapping("/viewAllPharmacientRA")
	public List<Pharmacient> getAllPharmacients() {
		// System.out.println("get item details"+file);
		List<Pharmacient> pharmacients = pharmacientService.getAllPharmacient();
		System.out.println("Done");
		return pharmacients;

	}
	
	@GetMapping("/viewPharmacientByItemRA/{id}")
	public Pharmacient viewPharmacientByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		Pharmacient pharmacient = pharmacientService.viewItemByID(id);
		

		return pharmacient;

	}
	
	@DeleteMapping("/deletePharmacientRA/{id}")
	public void deletePharmacient(@PathVariable Long id) {
		//pharmacientService.deleteItem(id);

		//return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
	
	
}
