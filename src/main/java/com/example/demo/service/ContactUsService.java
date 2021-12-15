package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.ContactUs;

public interface ContactUsService {
	public List<ContactUs> getAllContactUsDetails();
	public ContactUs addNewContactusDetails(ContactUs contactus);
	public ContactUs getContactUsDetailsById(String id);
	public ContactUs getContactUsDetailsByIdAPI(Long id);
	public ContactUs upadateContactUSDetails( Long id, String answer,String email);
	public ResponseEntity<Map<String,Boolean>> deleteContactUs(Long id);
}
