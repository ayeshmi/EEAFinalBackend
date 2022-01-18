package com.example.demo.controller.webController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	// add new item
	@PostMapping("/addItem")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView AddItem(@RequestParam("image") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("specifications") String specifications,
			@RequestParam("price") String price, @RequestParam("ingredients") String ingredients,
			@RequestParam("delivery") String delivery, @RequestParam("suitableFor") String suitableFor,
			@RequestParam("howToUse") String howToUse, @RequestParam("returnItem") String returnItem,
			@RequestParam("itemType") String itemType) {

		MessageResponse message = null;
		ModelAndView modelAndView = new ModelAndView();

		message = itemService.addItem(file, name, description, specifications, price, ingredients, delivery,
				suitableFor, howToUse, returnItem, itemType);

		if (message != null) {
			if (message.getMessage().equals("Error: This item is already in use!")) {
				modelAndView.addObject("ErrorMessage", message);
				modelAndView.setViewName("AddItem");
			} else {
				Item item = itemService.viewItemByName(name);
				modelAndView.addObject("item", item);
				modelAndView.addObject("ErrorMessage", message);
				modelAndView.setViewName("AddItemSuccess");
			}
		} else {
			modelAndView.setViewName("AddItem");
			MessageResponse response = new MessageResponse("Check user inputs and try again.");
			modelAndView.addObject("ErrorMessage", response);
		}

		return modelAndView;
	}

	// view all items
	@GetMapping("/viewAllItem")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView getAllItems() {
		ModelAndView modelAndView = new ModelAndView();

		List<Item> items = itemService.getAllItems();

		if (items != null) {
			modelAndView.addObject("items", items);
			modelAndView.setViewName("ViewItemCategorical");
		} else {
			MessageResponse response = new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewItemCategorical");
		}

		return modelAndView;
	}

	// view all items for selected category
	@GetMapping("/viewSelectedCategoryItem/{name}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView getSelectedCategoryItem(@PathVariable("name") String name) {
		ModelAndView modelAndView = new ModelAndView();

		List<Item> items = itemService.getSelectedCategoryItem(name);

		if (items.size() != 0) {
			modelAndView.addObject("items", items);
			modelAndView.setViewName("ViewSelectedCategoryItemSe");
		} else {
			MessageResponse response = new MessageResponse("No machers found!");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewItemCategorical");
		}

		return modelAndView;

	}

	// view selected item details
	@GetMapping("/viewItemByItem/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView viewItemByID(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();

		Item item = itemService.viewItemByID(id);
		if (item != null) {
			List<Comment> viewComments = commentService.getCommentByItemId(id);
			modelAndView.addObject("item", item);
			modelAndView.addObject("comments", viewComments);

		} else {
			MessageResponse response = new MessageResponse("No machers found!");
			modelAndView.addObject("ErrorMessage", response);

		}

		modelAndView.setViewName("ViewSelectedItemDetails");
		return modelAndView;

	}

	// advance item search
	@GetMapping("/advanceItemSearch")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView advanceItemSearch(@RequestParam("search") String search) {
		ModelAndView modelAndView = new ModelAndView();

		List<Item> items = itemService.advanceItemSearch(search);

		if (items.size() != 0) {
			modelAndView.addObject("items", items);
		} else {
			MessageResponse message = new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", message);
		}
		modelAndView.setViewName("ViewAllItemsTable");
		return modelAndView;

	}

	
	@GetMapping("/advanceItemSearchUser")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView advanceItemSearchUser(@RequestParam("search") String search) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Item> items = itemService.advanceItemSearch(search);
		
		if(items.size() != 0) {
			modelAndView.addObject("items", items);
		}else {
			MessageResponse message = new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", message);
		}

		modelAndView.setViewName("ViewSelectedCategoryItemSe");
		return modelAndView;

	}

	
	@GetMapping("/viewItemUpdateByItem/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView viewItemUpdateByID(@PathVariable("id") Long id) {
		
		Item item = itemService.viewItemByID(id);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("item", item);
		modelAndView.setViewName("UpdateSelectedItem");

		return modelAndView;

	}

	//update item details
	@PostMapping("/updateItem")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView UpdateItem(@RequestParam("image") MultipartFile file,
			@RequestParam("description") String description, @RequestParam("specifications") String specifications,
			@RequestParam("price") String price, @RequestParam("ingredients") String ingredients,
			@RequestParam("delivery") String delivery, @RequestParam("suitableFor") String suitableFor,
			@RequestParam("howToUse") String howToUse, @RequestParam("returnItem") String returnItem,
			@RequestParam("name11") Long name, @RequestParam("availability") String availability) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		MessageResponse message = null;
		message = itemService.updateItem(file, description, specifications, price, ingredients, delivery, suitableFor,
				howToUse, returnItem, name, availability);
		
		
		if (message != null) {
			List<Item> items = itemService.getAllItems();
			modelAndView.addObject("items", items);
			MessageResponse response = new MessageResponse("Item details are Successfully updated.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewAllItemsTable");
		} else {
			MessageResponse response = new MessageResponse("Item list is empty.");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewItemCategorical");
		}
		return modelAndView;
	}


	@RequestMapping("/addItemTable")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView viewAllItemAdming() {
		List<Item> items = itemService.viewAllItems();

		ModelAndView modelAndView = new ModelAndView();
		
		if(items.size()!=0) {
			modelAndView.addObject("items", items);
			modelAndView.setViewName("ViewAllItemsTable");
		}else {
			MessageResponse response = new MessageResponse("Item list is empty.");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewAllItemsTable");
		}

		return modelAndView;

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

	//delete item from the system
	@GetMapping("/deleteItem")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView deleteItem(@RequestParam("itemId") Long id) {

		MessageResponse response = itemService.deleteItem(id);
		List<Item> items = itemService.viewAllItems();
		ModelAndView modelAndView = new ModelAndView();
		if (items.size() != 0) {

			modelAndView.addObject("ErrorMessage", response);
			modelAndView.addObject("items", items);
			modelAndView.setViewName("ViewAllItemsTable");
		}

		else {
			MessageResponse message = new MessageResponse("Something went wrong, try again!");
			modelAndView.setViewName("ViewAllItemsTable");
			modelAndView.addObject("errorMsg", message);
		}
		return modelAndView;

	}


}
