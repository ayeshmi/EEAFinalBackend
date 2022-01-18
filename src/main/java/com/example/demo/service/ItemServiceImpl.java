package com.example.demo.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Item;
import com.example.demo.dto.MessageResponse;
import com.example.demo.repository.ItemRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;


@Service
public class ItemServiceImpl implements ItemService{
	
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private FileStorageServiceImpl fileStorageService;
	
	//add new item
	@Override
	public MessageResponse addItem(Item item) {
		
		MessageResponse message=null;
		try {
			if (itemRepo.existsByName(item.getName())) {
				message = new MessageResponse("Error: Item name is already in use!");
				
			}
			else {
				message = new MessageResponse("Item is successfully added.");
				item.setAvailability("Available");
				itemRepo.save(item);	
			}
			
		}catch(ResourceNotFoundException e)
		{
			e.printStackTrace();
		}
		return message;
	}

	//add new item
	@Override
	public MessageResponse addItem(MultipartFile file, String name, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem,String itemType) {
		
		MessageResponse message=null;
		try {
			
			if (itemRepo.existsByName(name)) {
				message = new MessageResponse("Error: Item name is already in use!");

				return message;
			}
			else {
				Item p = new Item();
				String imagePath=imageUploader(file);
				String fileName = fileStorageService.storeFile(file);
				p.setImageName(fileName);
				p.setImage(imagePath);	
		        p.setAvailability("Available"); 
		        p.setName(name);
		        p.setPrice(price);
		        p.setDelivery(delivery);
		        p.setDescription(description);
		        p.setHowToUse(howToUse);
		        p.setIngredients(ingredients);
		        p.setSpecifications(specifications);
		        p.setSuitableFor(suitableFor);
		        p.setReturnItem(returnItem);
		        p.setItemType(itemType);
		        
		        itemRepo.save(p);
		        message=new MessageResponse("Item is successfully Added.");
			}
		
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	//add image of the item
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
			e.printStackTrace();
		}

		return fileDownloadUri;
	}

	//get all items
	@Override
	public List<Item> getAllItems() {
		List<Item> items=null;
		try {
			 items=itemRepo.findAll();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return items;
	}

	//get items for selected category 
	@Override
	public List<Item> getSelectedCategoryItem(String category) {
		
		List<Item> items=null;
		try {
			 items=itemRepo.findBySpecifications(category);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return items;
	}

	//view items by ID
	@Override
	public Item viewItemByID(Long id) {
		
		Item item=null;
		try {
			 item=itemRepo.findById(id).orElseThrow();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return item;
	}
	
	//view items by name
	@Override
    public Item viewItemByName(String name) {
		
		Item item=null;
		try {
			 item=itemRepo.findByName(name);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return item;
	}
	
	//get all items
	@Override
	public List<Item> viewAllItems() {
		List<Item> items=null;
		try {
			 items=itemRepo.findAll();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return items;
		
	}

	//delete item details
	@Override
	public MessageResponse deleteItem(Long id) {
		MessageResponse message=null;
		try {
			Item item=itemRepo.findById(id)
					.orElseThrow();
			itemRepo.foreigKeyProblem();
			itemRepo.delete(item);
			message=new MessageResponse("Item is successfully deleted."); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return message;
	}

	//update item details
	@Override
	public MessageResponse updateItem(MultipartFile file, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem,Long id,String availability) {
		MessageResponse message=null;
		try {
			Item p =itemRepo.findById(id)
					.orElseThrow();
			System.out.println("dsdsds"+p);
			String imagePath=imageUploader(file);
			p.setImage(imagePath);
	        p.setPrice(price);
	        p.setDelivery(delivery);
	        p.setDescription(description);
	        p.setHowToUse(howToUse);
	        p.setIngredients(ingredients);
	        p.setAvailability(availability);
	        p.setSpecifications(specifications);
	        p.setSuitableFor(suitableFor);
	        p.setReturnItem(returnItem);
	        
	        itemRepo.save(p);
	        message=new MessageResponse("Item is successfully Updated."); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	//update item by ID
	@Override
	public MessageResponse updateItembyID(Item item, Long id) {
		MessageResponse message=null;
		try {
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
			System.out.println("updated123");
			message=new MessageResponse("Item details are successfully updated.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	//advance search of the items
	@Override
	public List<Item> advanceItemSearch(String search) {
		List<Item> items=null;
	     try {
	    	 items=itemRepo.search(search);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
		return items;
	}
	
        
}
