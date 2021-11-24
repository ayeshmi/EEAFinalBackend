package com.example.demo.controller.webController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/addToCart/{itemId}")
	public void addOrder(@RequestParam("clientEmail") String clientEmail,@RequestParam("userId") Long userId,
			@RequestParam("price") String price,@RequestParam("quantity") String quantity,@PathVariable Long itemId){
		//System.out.println("sdsd"+order.getClientEmail());
		
	orderService.addToCartItem(clientEmail,userId,price,quantity,itemId,java.time.LocalDate.now());
	System.out.println(java.time.LocalDate.now());  
		System.out.println("Item is sucessfully added"+quantity);
		
	}
	
	@GetMapping("/addOrder/{email}")
	public void orderConfirmation(@PathVariable String email){
		//System.out.println("sdsd"+order.getClientEmail());
		
	orderService.orderConfirmation(email);  
		System.out.println("order is sucessfully added");
		
	}
	
	
	@GetMapping("/viewCart/{id}")
	public ModelAndView viewCartDetailsUser(@PathVariable Long id) {
        List<Order> orders=orderService.viewCartDetailsUser(id);
        List<Item> itemList=new ArrayList<Item>();
        
        int totalPrice=0;
        int priceFullTotal=0;
        for(int i=0;i<orders.size();i++) {
        	Order order1=orders.get(i);
        Item item=	itemService.viewItemByID(order1.getItemId());
        int itemPrice = Integer.parseInt(order1.getPrice());
        int quantity = Integer.parseInt(order1.getQuantity());
        totalPrice=itemPrice*quantity;
        priceFullTotal=priceFullTotal+totalPrice;
        itemList.add(item);
			
		}
		ModelAndView modelAndView = new ModelAndView();
        System.out.println("ff"+priceFullTotal);
		modelAndView.addObject("orders", orders);
		modelAndView.addObject("items", itemList);
		modelAndView.addObject("totalPrice", priceFullTotal);
		modelAndView.setViewName("ViewCart");

		return modelAndView;
		
	}

	@RequestMapping("/orderConfirmation/{totalPrice}")
	public ModelAndView orderPage(@PathVariable int totalPrice) {
		int deliveryFee=250;
		int totalFee=deliveryFee+totalPrice;
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("totalPrice", totalPrice);
		modelAndView.addObject("deliveryFee", deliveryFee);
		modelAndView.addObject("totalFee", totalFee);
		modelAndView.setViewName("Order");
		return modelAndView;
	}
	

	@GetMapping("/deletecartItem/{id}")
	public void deleteCartItem(@PathVariable Long id) {
		orderService.deleteItem(id);

		//return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
}
