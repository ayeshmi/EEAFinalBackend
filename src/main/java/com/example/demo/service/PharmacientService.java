package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.model.Pharmacient;

public interface PharmacientService {
	public MessageResponse addPharmacient(MultipartFile file, String firstName, String lastName, String contactNumber,
			String email, String address);
	public String imageUploader(MultipartFile file);
	public List<Pharmacient> getAllPharmacient();
	public Pharmacient viewItemByID(Long id) ;
	public MessageResponse deletePharmacient(Long id);
	public void addPharmacientAPI(Pharmacient pharmacient);
	public MessageResponse addAttendence(String email, String startTime, String endTime,String username,Long id);
	public List<Order> viewOrdersPharmacist(Long id);
}
