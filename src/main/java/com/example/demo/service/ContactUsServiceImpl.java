package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

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

	@Override
	public List<ContactUs> getAllContactUsDetails() {
		return contactusRepository.findAll();
	}

	@Override
	public MessageResponse addNewContactusDetails(ContactUs contactus) {
		MessageResponse messageResponse = null;
		try {
			contactusRepository.save(contactus);
			messageResponse = new MessageResponse("Your contactUs request is successfully added.");
		} catch (Exception e) {
			System.out.println("Error is " + e);
		}

		return messageResponse;
	}

	@Override
	public ContactUs getContactUsDetailsById(String id) {
		ContactUs contactUs = null;
		try {
			Long Id = Long.parseLong(id);
			contactUs = contactusRepository.findById(Id).orElseThrow();

		} catch (Exception e) {
			System.out.println("Error is " + e);
		}

		return contactUs;
	}

	@Override
	public ContactUs getContactUsDetailsByIdAPI(Long id) {
		ContactUs contactUs = null;
		try {
			contactUs = contactusRepository.findById(id).orElseThrow();

		} catch (Exception e) {
			System.out.println("Error is " + e);
		}

		return contactUs;
	}

	@Override
	public ContactUs upadateContactUSDetails(Long id, String answer, String email) {
		ContactUs updateContact = null;
		try {
			ContactUs contactUs = contactusRepository.findById(id).orElseThrow();
			contactUs.setAnswer(answer);
			updateContact = contactusRepository.save(contactUs);
			emailSender.sendEmailContactUs(email, answer);
		} catch (Exception e) {
			System.out.println("Error is " + e);
		}

		return updateContact;
	}

	@Override
	public MessageResponse deleteContactUs(Long id) {
		MessageResponse messageResponse = null;
		try {

			ContactUs contactUs = contactusRepository.findById(id).orElseThrow();
			contactusRepository.delete(contactUs);
			messageResponse = new MessageResponse("Contact Us Detail is sucessfully deleted.");
		} catch (Exception e) {
			System.out.println("Error is " + e);
		}

		return messageResponse;
	}

	@Override
	public MessageResponse addNewContactusDetailswithUser(@Valid ContactUs contactus, Long id) {
		MessageResponse messageResponse = null;
		try {
			User user = userRepository.findById(id).orElseThrow();
			contactus.setUser(user);
			contactusRepository.save(contactus);
			messageResponse = new MessageResponse("Your contactUs request is successfully added.");
		} catch (Exception e) {
			System.out.println("Error is " + e);
		}

		return messageResponse;

	}

	public List<ContactUs> viewContactUsForUser(Long id) {
		List<ContactUs> contactList = null;
		try {
			contactList = contactusRepository.findByUserID(id);

		} catch (Exception e) {
			System.out.println("Error is " + e);
		}
		return contactList;

	}

}
