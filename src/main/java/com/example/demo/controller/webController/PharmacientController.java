package com.example.demo.controller.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Item;
import com.example.demo.model.Pharmacient;
import com.example.demo.service.PharmacientService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class PharmacientController {
	
	@Autowired
	private PharmacientService pharmacientService;

	
	@PostMapping("/addPharmacient")
	public void addPharmacient(@RequestParam("image") MultipartFile file, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("contactNumber") String contactNumber,
			@RequestParam("email") String email, @RequestParam("address") String address) {
		// System.out.println("get item details"+file);
		pharmacientService.addPharmacient(file,firstName,lastName,contactNumber,email,address);

		System.out.println("Request is leanded" + file);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Home");

		// return modelAndView;

	}
	
	@GetMapping("/viewAllPharmacient")
	public ModelAndView getAllPharmacients() {
		// System.out.println("get item details"+file);
		List<Pharmacient> pharmacients = pharmacientService.getAllPharmacient();
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("pharmacients", pharmacients);
		modelAndView.setViewName("ViewAllPharmacient");

		return modelAndView;

	}
	
	@GetMapping("/viewPharmacientByItem/{id}")
	public ModelAndView viewPharmacientByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		Pharmacient pharmacient = pharmacientService.viewItemByID(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("pharmacient", pharmacient);
		modelAndView.setViewName("viewSelectedPharmacientDetail");

		return modelAndView;

	}
	
	@GetMapping("/deletePharmacient/{id}")
	public void deletePharmacient(@PathVariable Long id) {
		pharmacientService.deleteItem(id);

		//return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
	@RequestMapping("/addPharmacientPage")
	public String addItemPage() {
		return "AddPharmacient";
	}
	
}
