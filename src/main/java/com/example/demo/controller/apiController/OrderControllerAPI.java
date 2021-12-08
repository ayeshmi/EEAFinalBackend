package com.example.demo.controller.apiController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class OrderControllerAPI {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/addToCartRA")
	public void addOrder(@RequestBody Order order){
		//System.out.println("sdsd"+order.getClientEmail());
		
	orderService.addToCartItem(order.getClientEmail(),order.getUserId(),order.getPrice(),order.getQuantity(),order.getItemId(),java.time.LocalDate.now(),order.getItemName());
	System.out.println(java.time.LocalDate.now());  
		System.out.println("Item is sucessfully added to cart");
		
	}
	
	@GetMapping("/addOrderRA/{email}")
	public void orderConfirmation(@PathVariable String email){
		//System.out.println("sdsd"+order.getClientEmail());
		
	orderService.orderConfirmation(email);  
		System.out.println("order is sucessfully added");
		
	}
	
	@GetMapping("/OrderCompletedRA/{itemId}")
	public void orderCompleted(@PathVariable Long itemId){
		//System.out.println("sdsd"+order.getClientEmail());
		
	orderService.orderCompleted(itemId);  
		System.out.println("order is sucessfully completed");
		
	}
	
	@GetMapping("/OrderCancelationRA/{itemId}")
	public void orderCancelation(@PathVariable Long itemId){
		//System.out.println("sdsd"+order.getClientEmail());
		
	orderService.orderCancelation(itemId);  
		System.out.println("order is sucessfully completed");
		
	}
	
	
	@GetMapping("/viewCartRA/{id}")
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
	
	@GetMapping("/viewOrderRA/{id}")
	public ModelAndView viewOrderDetailsUser(@PathVariable Long id) {
        List<Order> orders1=orderService.viewOrderDetailsUser(id);
        System.out.println("size"+orders1.size());
        List<Item> itemList1=new ArrayList<Item>();
        
        int totalPrice=0;
        int priceFullTotal=0;
        for(int i=0;i<orders1.size();i++) {
        	Order order1=orders1.get(i);
        Item item=	itemService.viewItemByID(order1.getItemId());
        int itemPrice = Integer.parseInt(order1.getPrice());
        int quantity = Integer.parseInt(order1.getQuantity());
        totalPrice=itemPrice*quantity;
        priceFullTotal=priceFullTotal+totalPrice;
        itemList1.add(item);
			
		}
        System.out.println("sdsdsddd"+itemList1.size());
		ModelAndView modelAndView = new ModelAndView();
        System.out.println("ff"+priceFullTotal);
		modelAndView.addObject("ordersO", orders1);
		//modelAndView.addObject("itemsO", itemList1);
		modelAndView.addObject("totalPrice", priceFullTotal);
		modelAndView.setViewName("OrderDetails");

		return modelAndView;
		
	}
	
	
	

	@RequestMapping("/orderConfirmationRA/{totalPrice}")
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
	

	@GetMapping("/deletecartItemRA/{id}")
	public void deleteCartItem(@PathVariable Long id) {
		orderService.deleteItem(id);

		//return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
}
