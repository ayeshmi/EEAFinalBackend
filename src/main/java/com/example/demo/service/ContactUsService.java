package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.ContactUs;
import com.example.demo.model.EmailSender;
import com.example.demo.repository.ContactUsRepository;

@Service
public class ContactUsService {
	@Autowired
	ContactUsRepository contactusRepository;
	
	@Autowired
	EmailSender emailSender;
	
	
	public List<ContactUs> getAllContactUsDetails(){
		return contactusRepository.findAll();
	}
	
	public ContactUs addNewContactusDetails(ContactUs contactus) {
		return contactusRepository.save(contactus);
	}
	
	public ContactUs getContactUsDetailsById(String id){
		Long Id=Long.parseLong(id);
		ContactUs contactUs=contactusRepository.findById(Id) 
				.orElseThrow();
		return contactUs;
	}
	
	public ContactUs getContactUsDetailsByIdAPI(Long id){
		
		ContactUs contactUs=contactusRepository.findById(id) 
				.orElseThrow();
		System.out.println("caled");
		return contactUs;
	}
	
	public ContactUs upadateContactUSDetails( Long id, String answer,String email){
		
		ContactUs contactUs=contactusRepository.findById(id)
				.orElseThrow();
				
		contactUs.setAnswer(answer);
	ContactUs updateContact=contactusRepository.save(contactUs);
	emailSender.sendEmailContactUs(email, answer);
		return updateContact;
	}
	
	public ResponseEntity<Map<String,Boolean>> deleteContactUs(Long id){
		ContactUs contactUs=contactusRepository.findById(id)
				.orElseThrow();
		contactusRepository.delete(contactUs);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
