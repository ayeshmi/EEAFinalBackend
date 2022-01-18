package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.ContactUs;
import com.example.demo.model.EmailSender;
import com.example.demo.model.User;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.repository.UserRepository;


@Service
public class ContactUsServiceImpl implements ContactUsService {
	@Autowired
	private ContactUsRepository contactusRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailSender emailSender;

	//find all contact requests from database
	@Override
	public List<ContactUs> getAllContactUsDetails() {
		List<ContactUs> contactus=null;
		
		try {
			contactus= contactusRepository.findAllContactUs();
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		return contactus;
	}

	//find new record to the database
	@Override
	public MessageResponse addNewContactusDetails(ContactUs contactus) {
		MessageResponse messageResponse = null;
		
		try {
			contactusRepository.save(contactus);
			messageResponse = new MessageResponse("Your contactUs request is successfully added.");
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return messageResponse;
	}

	//find contact us request by ID
	@Override
	public ContactUs getContactUsDetailsById(String id) {
		Optional<ContactUs> batchOptional = contactusRepository.findById(Long.parseLong(id));
		ContactUs contactUs = null;
		 if (batchOptional.isPresent()) {

			 contactUs = batchOptional.get();

	        } else {

	           System.out.println("Contact us detail is not found");
	        }


		return contactUs;
	}

	@Override
	public ContactUs getContactUsDetailsByIdAPI(Long id) {
		 Optional<ContactUs> batchOptional = contactusRepository.findById(id);
	        ContactUs batch = null;

	        if (batchOptional.isPresent()) {

	            batch = batchOptional.get();

	        } else {

	           System.out.println("Contact us detail is not found");
	        }

	        return batch;
	}

	//update contact us request detail
	@Override
	public MessageResponse upadateContactUSDetails(Long id, String answer, String email) {
		
		MessageResponse messageResponse = null;
		
		try {
			ContactUs contactUs = contactusRepository.findById(id).orElseThrow();
			contactUs.setAnswer(answer);
			contactusRepository.save(contactUs);
			messageResponse = new MessageResponse("Your reply is successfully sent to customer.");
			emailSender.sendEmailContactUs(email, answer);
			
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return messageResponse;
	}

	//delete contact us request
	@Override
	public MessageResponse deleteContactUs(Long id) {
		
		MessageResponse messageResponse = null;
		
		try {
			ContactUs contactUs = contactusRepository.findById(id).orElseThrow();
			contactusRepository.delete(contactUs);
			messageResponse = new MessageResponse("Contact Us Detail is sucessfully deleted.");
			
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return messageResponse;
	}

	//add contact us request by registered user
	@Override
	public MessageResponse addNewContactusDetailswithUser(@Valid ContactUs contactus, Long id) {
		
		MessageResponse messageResponse = null;
		
		try {
			User user = userRepository.findById(id).orElseThrow();
			contactus.setUser(user);
			contactusRepository.save(contactus);
			messageResponse = new MessageResponse("Your contactUs request is successfully added.");
			
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return messageResponse;

	}

	
	@Override
	public List<ContactUs> viewContactUsForUser(Long id) {
		
		List<ContactUs> contactList = null;
		try {
			contactList = contactusRepository.findByUserID(id);

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		return contactList;

	}

}
