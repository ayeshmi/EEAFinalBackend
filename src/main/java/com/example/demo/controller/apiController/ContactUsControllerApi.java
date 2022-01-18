package com.example.demo.controller.apiController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContactUs;
import com.example.demo.dto.MessageResponse;
import com.example.demo.service.ContactUsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ContactUsControllerApi {
	@Autowired
	ContactUsServiceImpl contactusService;

	// this method is used to add new contact us deatils to the system
	@PostMapping("/contactusRA/{Id}")
	public ResponseEntity<Object> addNewContactusDetails(@Valid @RequestBody ContactUs contactus,
			@PathVariable("Id") Long id) {
		MessageResponse message = null;
		message = contactusService.addNewContactusDetailswithUser(contactus, id);

		if (message != null) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			MessageResponse messageError = new MessageResponse("Something went wrong, try again.");
			return new ResponseEntity<>(messageError, HttpStatus.OK);
		}

	}

	// this method is used to view all contactus details
	@GetMapping("/allConatctUsRA")
	public ResponseEntity<Object> getAllContactUsDetails() {
		List<ContactUs> list = contactusService.getAllContactUsDetails();
		if (list.size() != 0) {

			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {

			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}

	}

	// this method is used to view all contactus details for peruser
	@GetMapping("/allUserConatctUsPerUserRA/{Id}")
	public ResponseEntity<Object> getAllContactUsDetailsPerUser(@PathVariable("Id") Long id) {
		List<ContactUs> list = contactusService.viewContactUsForUser(id);
		if (list.size() != 0) {

			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {

			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}

	}

	// this method is used delete a contactus details from the database
	@DeleteMapping("/deleteContactUsRA/{id}")
	public ResponseEntity<MessageResponse> deleteContactUs(@PathVariable Long id) {
		contactusService.deleteContactUs(id);

		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}

	// view contact us detail by ID
	@GetMapping("/contactusDetailsRA/{id}")
	public ContactUs getContactUsDetailsById(@PathVariable("id") Long id) {

		ContactUs contactUs = contactusService.getContactUsDetailsByIdAPI(id);
		return contactUs;
	}

}
