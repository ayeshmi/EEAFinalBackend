package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Item;
import com.example.demo.model.Pharmacient;
import com.example.demo.repository.PharmacientRepository;

@Service
public class PharmacientService {
	
	@Autowired
	private PharmacientRepository pharmacientRepository;
	
	@Autowired
	private FileStorageService fileStorageService;

	public void addPharmacient(MultipartFile file, String firstName, String lastName, String contactNumber,
			String email, String address) {
		// TODO Auto-generated method stub
		System.out.println("Called");
		Pharmacient p = new Pharmacient();
		String imagePath=imageUploader(file);
		String fileName = fileStorageService.storeFile(file);
		p.setImageName(fileName);
		p.setImage(imagePath);
		p.setFirstName(firstName);	
		p.setAddress(address);
		p.setContactNumber(contactNumber);
		p.setEmail(email);
		p.setLastName(lastName);
       
        
		pharmacientRepository.save(p);
		
	}
	public String imageUploader(MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/image/")
				.path(fileName)
				.toUriString();
		
		return fileDownloadUri;
	}
	public List<Pharmacient> getAllPharmacient() {
		// TODO Auto-generated method stub
		List<Pharmacient> pharmacients=pharmacientRepository.findAll();
		return pharmacients;
	}
	public Pharmacient viewItemByID(Long id) {
		Pharmacient pharmacient=pharmacientRepository.findById(id)
				.orElseThrow();
		return pharmacient;
	}
	public void deleteItem(Long id) {
		Pharmacient pharmacient=pharmacientRepository.findById(id)
				.orElseThrow();
		pharmacientRepository.delete(pharmacient);
		System.out.println("Deleted");
		
	}
	public void addPharmacientAPI(Pharmacient pharmacient) {
		pharmacientRepository.save(pharmacient);
		
	}

}
