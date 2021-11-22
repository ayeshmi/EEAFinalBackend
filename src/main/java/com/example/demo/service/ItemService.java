package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Item;

import com.example.demo.repository.ItemRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;

@Service
public class ItemService {
	
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	
	


	public void addItem(Item item) {
	
		item.setImage("http://localhost:8080/api/auth/"+item.getImage());
		
		itemRepo.save(item);
		System.out.println("Process is completed");
	
	}

	public void addItem(MultipartFile file, String name, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem) {
		System.out.println("Called");
		Item p = new Item();
		String imagePath=imageUploader(file);
		p.setImage(imagePath);
		
		
		
        p.setName(name);
        p.setPrice(price);
        p.setDelivery(delivery);
        p.setDescription(description);
        p.setHowToUse(howToUse);
        p.setIngredients(ingredients);
        p.setAvailability("Available");
        p.setSpecifications(specifications);
        p.setSuitableFor(suitableFor);
        p.setReturnItem(returnItem);
        
        itemRepo.save(p);
		
	}
	
	public String imageUploader(MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/auth/video/")
				.path(fileName)
				.toUriString();
		
		return fileDownloadUri;
	}

	public List<Item> getAllItems() {
		List<Item> items=itemRepo.findAll();
		return items;
	}

	public List<Item> getSelectedCategoryItem(String category) {
		// TODO Auto-generated method stub
		List<Item> items= itemRepo.findBySpecifications(category);
		return items;
	}

	public Item viewItemByID(Long id) {
		// TODO Auto-generated method stub
		Item item=itemRepo.findById(id).orElseThrow();
		
		return item;
	}
        
}
