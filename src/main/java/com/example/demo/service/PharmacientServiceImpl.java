package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Attendence;
import com.example.demo.model.Order;
import com.example.demo.model.Pharmacient;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.repository.AttendenceRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PharmacientRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PharmacientServiceImpl implements PharmacientService{
	
	@Autowired
	private PharmacientRepository pharmacientRepository;
	
	@Autowired
	private AttendenceRepository attendenceRepository;
	
	@Autowired
	private FileStorageServiceImpl fileStorageService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public MessageResponse addPharmacient(MultipartFile file, String firstName, String lastName, String contactNumber,
			String email, String address) {
		
		MessageResponse msg=null;
		try {
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
			msg=new MessageResponse("Pharmacient is successfully added.");
		}catch(Exception e) {
			e.printStackTrace();
		}

		return msg;
	}
	
	@Override
	public String imageUploader(MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/")
				.path(fileName)
				.toUriString();
		
		return fileDownloadUri;
	}
	
	@Override
	public List<Pharmacient> getAllPharmacient() {
		// TODO Auto-generated method stub
		List<Pharmacient> pharmacients=pharmacientRepository.findAll();
		return pharmacients;
	}
	
	@Override
	public Pharmacient viewItemByID(Long id) {
		Pharmacient pharmacient=pharmacientRepository.findById(id)
				.orElseThrow();
		return pharmacient;
	}
	
	@Override
	public MessageResponse deletePharmacient(Long id) {
		MessageResponse msg=null;
		try {
			Pharmacient pharmacient=pharmacientRepository.findById(id)
					.orElseThrow();
			pharmacientRepository.delete(pharmacient);
			msg=new MessageResponse("Pharamacist is successfully deleted");
		}catch(Exception e) {
			e.printStackTrace();
		}

		return msg;
	}
	
	@Override
	public void addPharmacientAPI(Pharmacient pharmacient) {
		pharmacientRepository.save(pharmacient);
		
	}

	@Override
	public MessageResponse addAttendence(String email, String startTime, String endTime,String username,Long id) {
		MessageResponse msg=null;
		try {
			Attendence attendence=new Attendence();
			attendence.setEmail(email);
			attendence.setLastTime(endTime);
			attendence.setStartTime(startTime);
			attendence.setUsername(username);
			attendence.setPharmacistID(id);
			attendence.setDate(java.time.LocalDate.now().toString());
			attendenceRepository.save(attendence);
			msg=new MessageResponse("Your Attendence is successfully added.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return msg;
		
	}
	
	public List<Attendence> getAvailablePharmacist(String date){
		List<Attendence> attendence=attendenceRepository.findByDate(date);
		return attendence;
	}

	@Override
	public List<Order> viewOrdersPharmacist(Long id) {
		List<Order> orders=null;
		try {
			
			User user=userRepository.findById(id)
					.orElseThrow();
			Pharmacient pharmacient=pharmacientRepository.findByFirstName(user.getUsername());
			System.out.println("heloo888"+pharmacient.getId());
			orders=orderRepository.viewOrdersPharmacist(pharmacient.getId());
			System.out.println("heloo888"+orders.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public List<User> advancePharmacistSearch(String search) {
		List<User> users=null;
		try {
			users=pharmacientRepository.search(search);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}




}
