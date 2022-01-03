package com.example.demo.controller.webController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Comment;
import com.example.demo.model.Item;
import com.example.demo.dto.MessageResponse;
import com.example.demo.service.CommentServiceImpl;
import com.example.demo.service.FileStorageServiceImpl;
import com.example.demo.service.ItemServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class ItemController {

	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private FileStorageServiceImpl fileStorageService;

	@Autowired
	private CommentServiceImpl commentService;

	@PostMapping("/addItem")
	public ModelAndView AddItem(@RequestParam("image") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("specifications") String specifications,
			@RequestParam("price") String price, @RequestParam("ingredients") String ingredients,
			@RequestParam("delivery") String delivery, @RequestParam("suitableFor") String suitableFor,
			@RequestParam("howToUse") String howToUse, @RequestParam("returnItem") String returnItem,
			@RequestParam("itemType") String itemType) {

		MessageResponse message = null;

		message = itemService.addItem(file, name, description, specifications, price, ingredients, delivery,
				suitableFor, howToUse, returnItem, itemType);
		ModelAndView modelAndView = new ModelAndView();
		 System.out.println("order is added");
		if (message != null) {
			if(message.getMessage().equals("Error: Item name is already in use!")) {
				modelAndView.addObject("ErrorMessage", message);
	    		modelAndView.setViewName("AddItem");	
			}
			else {
				 Item item=itemService.viewItemByName(name);
		            modelAndView.addObject("item", item);
		            modelAndView.addObject("ErrorMessage", message);
		    		modelAndView.setViewName("AddItemSuccess");	
			}
           
			
		} else {
			modelAndView.setViewName("AddItem");
			MessageResponse response =new MessageResponse("Check user inputs and try again.");
			modelAndView.addObject("ErrorMessage", response);
		}
		

		return modelAndView;

	}

	@GetMapping("/viewAllItem")
	public ModelAndView getAllItems() {
		// System.out.println("get item details"+file);
		List<Item> items = itemService.getAllItems();
		ModelAndView modelAndView = new ModelAndView();
		if(items != null) {
			modelAndView.addObject("items", items);
			modelAndView.setViewName("ViewItemCategorical");	
		}else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewItemCategorical");
		}
		
		return modelAndView;
	}

	@GetMapping("/viewSelectedCategoryItem/{name}")
	public ModelAndView getSelectedCategoryItem(@PathVariable("name") String name) {

		List<Item> items = itemService.getSelectedCategoryItem(name);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("items", items);
		modelAndView.setViewName("ViewSelectedCategoryItemSe");

		return modelAndView;

	}

	@GetMapping("/viewItemByItem/{id}")
	public ModelAndView viewItemByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		Item item = itemService.viewItemByID(id);
		List<Comment> viewComments = commentService.getCommentByItemId(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("item", item);
		modelAndView.addObject("comments", viewComments);
		modelAndView.setViewName("ViewSelectedItemDetails");

		return modelAndView;

	}
	
	@GetMapping("/advanceItemSearch")
	public ModelAndView advanceItemSearch(@RequestParam("search") String search) {
		// System.out.println("get item details"+file);
		System.out.println("Called12345");
		List<Item> items = itemService.advanceItemSearch(search);
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("items", items);
		modelAndView.setViewName("ViewAllItemsTable");
		

		return modelAndView;

	}

	@GetMapping("/viewItemUpdateByItem/{id}")
	public ModelAndView viewItemUpdateByID(@PathVariable("id") Long id) {
		// System.out.println("get item details"+file);
		System.out.println("Called1234");
		Item item = itemService.viewItemByID(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("item", item);
		modelAndView.setViewName("UpdateSelectedItem");

		return modelAndView;

	}

	@PostMapping("/updateItem")
	public ModelAndView UpdateItem(@RequestParam("image") MultipartFile file,
			@RequestParam("description") String description, @RequestParam("specifications") String specifications,
			@RequestParam("price") String price, @RequestParam("ingredients") String ingredients,
			@RequestParam("delivery") String delivery, @RequestParam("suitableFor") String suitableFor,
			@RequestParam("howToUse") String howToUse, @RequestParam("returnItem") String returnItem,@RequestParam("name11") Long name,@RequestParam("availability") String availability) {
	System.out.println("Calleddddddd"+name);
		MessageResponse message = null;
		message=itemService.updateItem(file, description, specifications, price, ingredients, delivery, suitableFor, howToUse,
				returnItem,name,availability);
		System.out.println("updated123");
		ModelAndView modelAndView = new ModelAndView();
		if(message !=null) {
			List<Item> items = itemService.getAllItems();
			modelAndView.addObject("items", items);
			MessageResponse response =new MessageResponse("Item details are Successfully updated.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewAllItemsTable");
		}
		else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewItemCategorical");
		}
		 return modelAndView;
	}

	@RequestMapping("/addItemPage")
	public String addItemPage() {
		return "AddItem";
	}

	@RequestMapping("/addItemTable")
	public ModelAndView viewAllItemAdming() {
		List<Item> items = itemService.viewAllItems();

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("items", items);
		modelAndView.setViewName("ViewAllItemsTable");

		return modelAndView;

	}

	@RequestMapping("/itemCategorical")
	public String addItemCategoricall() {
		return "ViewItemCategorical";
	}

	@GetMapping("video/{fileName:.+}")
	public ResponseEntity<Resource> downloadVideo(@PathVariable String fileName, HttpServletRequest request) {
		System.out.println(" get video url");
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		String contentType = null;

		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.println("Could not determine fileType");
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

	@GetMapping("/deleteItem")
	public ModelAndView deleteItem(@RequestParam("itemId") Long id) {
		
		MessageResponse response =itemService.deleteItem(id);
		List<Item> items = itemService.viewAllItems();	
		ModelAndView modelAndView = new ModelAndView();
		if(items.size() !=0) {
			MessageResponse message=new MessageResponse("Item is successfully deleted.");
		       //modelAndView = getLectures(lecture.getScheduledDate());
		       modelAndView.addObject("ErrorMessage",message);
		       modelAndView.addObject("items", items);
		       modelAndView.setViewName("ViewAllItemsTable");
		}
		
		
		else {
			MessageResponse message=new MessageResponse("Something went wrong, try again!");
			modelAndView.setViewName("ViewAllItemsTable");
		       modelAndView.addObject("ErrorMessage",message);
		}
      
		
		
     
	   
       return  modelAndView;

	}

}
