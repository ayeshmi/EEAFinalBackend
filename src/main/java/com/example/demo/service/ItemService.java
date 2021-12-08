package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.demo.model.Item;

import com.example.demo.repository.ItemRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {
	
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	
	


	public void addItem(Item item) {
		
		itemRepo.save(item);
		System.out.println("Process is completed");
	
	}

	public void addItem(MultipartFile file, String name, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem,String itemType) {
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
        p.setItemType(itemType);
        
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
		System.out.println("ayeshmi"+id);
		Item item=itemRepo.findById(id).orElseThrow();
		
		return item;
	}

	public List<Item> viewAllItems() {
		List<Item> items=itemRepo.findAll();
		return items;
		
	}

	public ResponseEntity<Map<String,Boolean>> deleteItem(Long id) {
		Item item=itemRepo.findById(id)
				.orElseThrow();
		itemRepo.delete(item);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	public void updateItem(MultipartFile file, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem) {
		// TODO Auto-generated method stub
		Item p = new Item();
		String imagePath=imageUploader(file);
		p.setImage(imagePath);
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

	public void updateItembyID(Item item, Long id) {
		Item itemUpdate=itemRepo.findById(id)
				.orElseThrow();
		itemUpdate.setImage(item.getImage());
		itemUpdate.setPrice(item.getPrice());
		itemUpdate.setDelivery(item.getDelivery());
		itemUpdate.setDescription(item.getDescription());
		itemUpdate.setHowToUse(item.getHowToUse());
		itemUpdate.setIngredients(item.getIngredients());
		//itemUpdate.setAvailability("Available");
		itemUpdate.setSpecifications(item.getSpecifications());
		itemUpdate.setSuitableFor(item.getSuitableFor());
		itemUpdate.setReturnItem(item.getReturnItem());
		itemRepo.save(itemUpdate);
		
	}
	
        
}
