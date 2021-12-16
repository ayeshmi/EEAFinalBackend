package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.ContactUs;
import com.example.demo.model.EmailSender;
import com.example.demo.repository.ContactUsRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService {
	@Autowired
	private ContactUsRepository contactusRepository;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public List<ContactUs> getAllContactUsDetails(){
		return contactusRepository.findAll();
	}
	
	@Override
	public MessageResponse addNewContactusDetails(ContactUs contactus) {
		MessageResponse messageResponse=null;
		try {
			contactusRepository.save(contactus);
			messageResponse=new MessageResponse("Your contactUs request is successfully added.");
		}catch(Exception e) {
			System.out.println("Error is "+e);
		}
		
		return messageResponse;
	}
	
	@Override
	public ContactUs getContactUsDetailsById(String id){
		Long Id=Long.parseLong(id);
		ContactUs contactUs=contactusRepository.findById(Id) 
				.orElseThrow();
		return contactUs;
	}
	
	@Override
	public ContactUs getContactUsDetailsByIdAPI(Long id){
		
		ContactUs contactUs=contactusRepository.findById(id) 
				.orElseThrow();
		System.out.println("caled");
		return contactUs;
	}
	
	@Override
	public ContactUs upadateContactUSDetails( Long id, String answer,String email){
		
		ContactUs contactUs=contactusRepository.findById(id)
				.orElseThrow();
				
		contactUs.setAnswer(answer);
	ContactUs updateContact=contactusRepository.save(contactUs);
	emailSender.sendEmailContactUs(email, answer);
		return updateContact;
	}
	
	@Override
	public ResponseEntity<Map<String,Boolean>> deleteContactUs(Long id){
		ContactUs contactUs=contactusRepository.findById(id)
				.orElseThrow();
		contactusRepository.delete(contactUs);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
