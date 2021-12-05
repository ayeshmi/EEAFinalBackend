package com.example.demo.controller.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Item;
import com.example.demo.model.MessageResponse;
import com.example.demo.service.ItemService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ItemControllerAPI {
	
	@Autowired
	private ItemService itemService;
	
	// adding new book
	@PostMapping("/addItemRA")
	public ResponseEntity<?> addItem(@RequestBody Item item) {
		System.out.println("Hello Book" + item.getDelivery());
		// bookService.addbook(book);
		itemService.addItem(item);
		return ResponseEntity.badRequest().body(new MessageResponse("Item is added successfully"));
	}
	
	@GetMapping("/viewAllItemRA")
	public List<Item> getAllItems() {
		// System.out.println("get item details"+file);
		//List<Item> items = itemService.getAllItems();
		
		return itemService.getAllItems();

	}
	
	@DeleteMapping("/deleteItemRA")
	public ResponseEntity<?> deleteItem(@PathVariable("itemId") Long id) {
		 System.out.println("Deletion");
		itemService.deleteItem(id);
        System.out.println("Deletion");
		return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
	@GetMapping("/viewItemUpdateByItemRA/{id}")
	public Item viewItemUpdateByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		Item item = itemService.viewItemByID(id);
		
		return item;

	}
	
	@PutMapping("/updateItem/{itemId}")
	public void UpdateItem(@RequestBody Item item,@PathVariable("itemId") Long id) {
		// System.out.println("get item details"+file);
		itemService.updateItembyID(item,id);
		System.out.println("Function is updated");

	


	}
	
	@GetMapping("/viewSelectedCategoryItemRA/{name}")
	public List<Item> getSelectedCategoryItem(@PathVariable("name") String name) {

		List<Item> items = itemService.getSelectedCategoryItem(name);
		
		return items;

	}

}
