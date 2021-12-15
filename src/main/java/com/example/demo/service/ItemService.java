package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Item;
import com.example.demo.dto.MessageResponse;

public interface ItemService {
	public void addItem(Item item);
	public MessageResponse addItem(MultipartFile file, String name, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem,String itemType);
	public String imageUploader(MultipartFile file);
	public List<Item> getAllItems();
	public List<Item> getSelectedCategoryItem(String category);
	public Item viewItemByID(Long id);
	public List<Item> viewAllItems();
	public ResponseEntity<Map<String,Boolean>> deleteItem(Long id);
	public MessageResponse updateItem(MultipartFile file, String description, String specifications, String price,
			String ingredients, String delivery, String suitableFor, String howToUse, String returnItem);
	public void updateItembyID(Item item, Long id);
}
