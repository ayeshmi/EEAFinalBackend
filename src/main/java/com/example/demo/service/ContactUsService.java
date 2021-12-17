package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.ContactUs;

public interface ContactUsService {
	public List<ContactUs> getAllContactUsDetails();
	public MessageResponse addNewContactusDetails(ContactUs contactus);
	public ContactUs getContactUsDetailsById(String id);
	public ContactUs getContactUsDetailsByIdAPI(Long id);
	public ContactUs upadateContactUSDetails( Long id, String answer,String email);
	public MessageResponse deleteContactUs(Long id);
	public MessageResponse addNewContactusDetailswithUser(@Valid ContactUs contactus, Long id);
}
