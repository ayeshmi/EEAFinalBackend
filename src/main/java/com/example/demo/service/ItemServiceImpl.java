package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.demo.model.Item;
import com.example.demo.dto.MessageResponse;
import com.example.demo.repository.ItemRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{
	
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private FileStorageServiceImpl fileStorageService;
	
	@Override
	public void addItem(Item item) {
		try {
			itemRepo.save(item);
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}
	
	}

	@Override
	public MessageResponse addItem(MultipartFile file, String name, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem,String itemType) {
		
		Item p = new Item();
		
		MessageResponse message=null;
		try {
			
			if (itemRepo.existsByName(name)) {
				message = new MessageResponse("Error: Item name is already in use!");

				return message;
			}
			String imagePath=imageUploader(file);
			String fileName = fileStorageService.storeFile(file);
			p.setImageName(fileName);
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
	        message=new MessageResponse("Item is successfully Added.");
		}catch(Exception e) {
			System.out.println("Error is" + e);
		}
		
		return message;
	}
	
	@Override
	public String imageUploader(MultipartFile file) {
		String fileDownloadUri=null;
		try {
			String fileName = fileStorageService.storeFile(file);
			 fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/auth/video/")
					.path(fileName)
					.toUriString();
		}
		catch(Exception e) {
			System.out.println("Error is" + e);
		}
		
		
		return fileDownloadUri;
	}

	@Override
	public List<Item> getAllItems() {
		List<Item> items=null;
		try {
			 items=itemRepo.findAll();
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}
		
		return items;
	}

	@Override
	public List<Item> getSelectedCategoryItem(String category) {
		
		List<Item> items=null;
		try {
			 items=itemRepo.findBySpecifications(category);
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}
		return items;
	}

	@Override
	public Item viewItemByID(Long id) {
		
		Item item=null;
		try {
			 item=itemRepo.findById(id).orElseThrow();
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}
		
		return item;
	}

    public Item viewItemByName(String name) {
		
		Item item=null;
		try {
			 item=itemRepo.findByName(name);
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}
		
		return item;
	}
	@Override
	public List<Item> viewAllItems() {
		List<Item> items=null;
		try {
			 items=itemRepo.findAll();
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}
		
		return items;
		
	}

	@Override
	public ResponseEntity<Map<String,Boolean>> deleteItem(Long id) {
		Item item=itemRepo.findById(id)
				.orElseThrow();
		itemRepo.delete(item);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@Override
	public MessageResponse updateItem(MultipartFile file, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem) {
		MessageResponse message=null;
		try {
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
	        message=new MessageResponse("Item is successfully Updated."); 
		}
		catch(Exception e) {
			System.out.println("Error is" + e);
		}
		return message;
	}

	@Override
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

	public List<Item> advanceItemSearch(String search) {
		List<Item> items=null;
	     try {
	    	 items=itemRepo.search(search);
	     }catch(Exception e) {
	    	 System.out.println("Error is" + e);
	     }
		return items;
	}
	
        
}
