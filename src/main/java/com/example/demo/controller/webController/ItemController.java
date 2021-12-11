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
import com.example.demo.model.MessageResponse;
import com.example.demo.service.CommentService;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.ItemService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private CommentService commentService;

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

			modelAndView.setViewName("AddItem");
			modelAndView.addObject("ErrorMessage", message);
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
			@RequestParam("howToUse") String howToUse, @RequestParam("returnItem") String returnItem) {
	
		MessageResponse message = null;
		message=itemService.updateItem(file, description, specifications, price, ingredients, delivery, suitableFor, howToUse,
				returnItem);
		ModelAndView modelAndView = new ModelAndView();
		if(message !=null) {
			
			modelAndView.setViewName("Home");
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

	@GetMapping("/deleteItem/{id}")
	public void deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);

		// return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}

}
