package com.example.demo.controller.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Item;
import com.example.demo.dto.MessageResponse;
import com.example.demo.service.ItemServiceImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ItemControllerAPI {
	
	@Autowired
	private ItemServiceImpl itemService;
	
	// adding new book
	@PostMapping("/addItemRA")
	public ResponseEntity<?> addItem(@RequestBody Item item) {
		MessageResponse message = null;
		message=itemService.addItem(item);
		
		if(message != null) {
			return ResponseEntity.ok(message);
		}
		else {
			return ResponseEntity.badRequest().body(new MessageResponse("Check input valus and try again!"));
		
		}
			
	}
	
	@GetMapping("/viewAllItemRA")
	public ResponseEntity<Object> getAllItems() {
		 List<Item> items=itemService.getAllItems();
		
		if(items.size() != 0) {
			return new ResponseEntity<>(items,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(items,HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping("/deleteItemRA/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable("itemId") Long id) {
		MessageResponse message = null;
		message=itemService.deleteItem(id);
        
		if(message != null) {
			return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));
		}
		else {
			return ResponseEntity.badRequest().body(new MessageResponse("No Record found!"));
		}

	}
	
	@GetMapping("/viewItemUpdateByItemRA/{id}")
	public ResponseEntity<Object> viewItemUpdateByID(@PathVariable("id") Long id) {
		Item item = itemService.viewItemByID(id);
		
		if(item != null) {
			return new ResponseEntity<>(item,HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>(item,HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping("/updateItem/{itemId}")
	public ResponseEntity<Object> UpdateItem(@RequestBody Item item,@PathVariable("itemId") Long id) {
		// System.out.println("get item details"+file);
		MessageResponse message = null;
		message=itemService.updateItembyID(item,id);
		
		if(message != null) {
			
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		else {
			message=new MessageResponse("Check inputs and try again.");
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
			
		}

	


	}
	
	@GetMapping("/viewSelectedCategoryItemRA/{name}")
	public ResponseEntity<Object> getSelectedCategoryItem(@PathVariable("name") String name) {
       
		List<Item> items = itemService.getSelectedCategoryItem(name);
		if(items.size() != 0) {
			return new ResponseEntity<>(items,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(items,HttpStatus.BAD_REQUEST);
		}
		

	}

}
