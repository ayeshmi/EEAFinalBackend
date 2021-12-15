package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Pharmacient;

public interface PharmacientService {
	public void addPharmacient(MultipartFile file, String firstName, String lastName, String contactNumber,
			String email, String address);
	public String imageUploader(MultipartFile file);
	public List<Pharmacient> getAllPharmacient();
	public Pharmacient viewItemByID(Long id) ;
	public void deleteItem(Long id);
	public void addPharmacientAPI(Pharmacient pharmacient);
}
