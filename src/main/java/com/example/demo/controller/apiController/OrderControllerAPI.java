package com.example.demo.controller.apiController;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.OrderServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class OrderControllerAPI {
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@Autowired
	private ItemServiceImpl itemService;
	
	@PostMapping("/addToCartRA/{id}")
	public void addOrder(@RequestBody Order order,@PathVariable("id") Long id){
		System.out.println("sdsd123"+order.getItemImage());
	
	orderService.addToCartItemAPI(order.getClientEmail(),id,order.getPrice(),order.getQuantity(),order.getItemId(),java.time.LocalDate.now(),order.getName(),order.getItemImage(),order.getImageName());
	
		
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
		
	//orderService.orderCancelation(itemId);  
		System.out.println("order is sucessfully completed");
		
	}
	
	
	@GetMapping("/viewCartRA/{id}")
	public List<Order> viewCartDetailsUser(@PathVariable Long id) {
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
		return null;
		
	}
	
	@GetMapping("/viewCancelOrdersRA/{id}")
	public List<Order> viewCancelOrders(@PathVariable Long id) {
        List<Order> orders=orderService.viewCancelOrders(id);
       
		return orders;
		
	}
	
	@GetMapping("/viewCompletedOrdersRA/{id}")
	public List<Order> viewCompletedOrders(@PathVariable Long id) {
        List<Order> orders=orderService.viewComletedOrders(id);
       
		return orders;
		
	}
	
	@GetMapping("/viewPendingOrdersRA/{id}")
	public List<Order> viewPendingOrders(@PathVariable Long id) {
        List<Order> orders=orderService.viewPendingOrders(id);
       
		return orders;
		
	}
	
	@GetMapping("/viewOrderRA/{id}")
	public List<Order> viewOrderDetailsUser(@PathVariable Long id) {
        List<Order> orders1=orderService.viewOrderDetailsUser(id);
        System.out.println("size"+orders1.size());
      
        
      
        

		return orders1;
		
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
	

	@DeleteMapping("/deletecartItemRA/{id}")
	public void deleteCartItem(@PathVariable Long id) {
		orderService.deleteItem(id);

		//return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));

	}
	
}
