package com.example.demo.controller.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Item;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.OrderServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;

	@Autowired
	private ItemServiceImpl itemService;

	@PostMapping("/addToCart/{itemId}")
	public ModelAndView addOrder(@RequestParam("clientEmail") String clientEmail,
			@RequestParam("userId") Long userId, @RequestParam("price") String price,
			@RequestParam("quantity") String quantity, @PathVariable Long itemId,
			@RequestParam("itemName12") String itemName,@RequestParam("image") String image) {
		MessageResponse response = null;
		
		response = orderService.addToCartItem(clientEmail, userId, price, quantity, itemId, java.time.LocalDate.now(),
				itemName,image);
		Item item=itemService.viewItemByID(itemId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", item);
        if(response !=null) {
      
			modelAndView.addObject("ErrorMessage", response);	
			modelAndView.setViewName("ViewSelectedItemDetails");
			
        }
        else {
        	MessageResponse response1 = new MessageResponse("Something went wrong, try again.");
        	modelAndView.addObject("ErrorMessage", response1);	
			modelAndView.setViewName("ViewSelectedItemDetails");
			
        }
		
        return modelAndView;

	}

	@GetMapping("/addOrder/{email}")
	public void orderConfirmation(@PathVariable String email) {
		// System.out.println("sdsd"+order.getClientEmail());
		orderService.orderConfirmation(email);
		System.out.println("order is sucessfully added");

	}

	@GetMapping("/OrderCompleted/{itemId}/{userId}")
	public ModelAndView orderCompleted(@PathVariable("itemId") Long itemId,@PathVariable("userId") Long userId) {

		MessageResponse response = null;
		ModelAndView modelAndView = new ModelAndView();
		response=orderService.orderCompleted(itemId);
		List<Order> orders1 = orderService.viewOrderDetailsUser(userId);
		if(response != null) {
			if (orders1.size() != 0) {
				int totalPrice = 0;
				int priceFullTotal = 0;
				for (int i = 0; i < orders1.size(); i++) {
				    Order order1 = orders1.get(i);	
					int itemPrice = Integer.parseInt(order1.getPrice());
					int quantity = Integer.parseInt(order1.getQuantity());
					totalPrice = itemPrice * quantity;
					priceFullTotal = priceFullTotal + totalPrice;
				}
				
				modelAndView.addObject("ordersO", orders1);
				modelAndView.addObject("totalPrice", priceFullTotal);
				modelAndView.setViewName("OrderDetails");
			} else {
				
				MessageResponse message = new MessageResponse("User list is empty.");
				modelAndView.addObject("ErrorMessage", message);
				modelAndView.setViewName("OrderDetails");
			}
			modelAndView.addObject("ErrorMessage", response);	
			modelAndView.setViewName("OrderDetails");
		}
		else {
			MessageResponse message = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.setViewName("OrderDetails");
		}
		
		return modelAndView;

	}

	@GetMapping("/OrderCancelation/{itemId}/{userId}")
	public ModelAndView orderCancelation(@PathVariable("itemId") Long itemId,@PathVariable("userId") Long userId) {
		MessageResponse response = null;
		ModelAndView modelAndView = new ModelAndView();
		response=orderService.orderCancelation(itemId);
		List<Order> orders1 = orderService.viewOrderDetailsUser(userId);
		if(response != null) {
			if (orders1.size() != 0) {
				int totalPrice = 0;
				int priceFullTotal = 0;
				for (int i = 0; i < orders1.size(); i++) {
				    Order order1 = orders1.get(i);	
					int itemPrice = Integer.parseInt(order1.getPrice());
					int quantity = Integer.parseInt(order1.getQuantity());
					totalPrice = itemPrice * quantity;
					priceFullTotal = priceFullTotal + totalPrice;
				}
				
				modelAndView.addObject("ordersO", orders1);
				modelAndView.addObject("totalPrice", priceFullTotal);
				modelAndView.setViewName("OrderDetails");
			} else {
				
				MessageResponse message = new MessageResponse("User list is empty.");
				modelAndView.addObject("ErrorMessage", message);
				modelAndView.setViewName("OrderDetails");
			}
			modelAndView.addObject("ErrorMessage", response);	
			modelAndView.setViewName("OrderDetails");
		}
		else {
			MessageResponse message = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.setViewName("OrderDetails");
		}
		
		return modelAndView;
		

	}

	@GetMapping("/viewCart/{id}")
	public ModelAndView viewCartDetailsUser(@PathVariable Long id) {
		
		List<Order> orders = orderService.viewCartDetailsUser(id);
		
		ModelAndView modelAndView = new ModelAndView();
		int totalPrice = 0;
		int priceFullTotal = 0;
		if(orders.size() != 0) {
			for (int i = 0; i < orders.size(); i++) {
				Order order1 = orders.get(i);
				int itemPrice = Integer.parseInt(order1.getPrice());
				int quantity = Integer.parseInt(order1.getQuantity());
				totalPrice = itemPrice * quantity;
				priceFullTotal = priceFullTotal + totalPrice;
			}
			modelAndView.addObject("orders", orders);
			modelAndView.addObject("totalPrice", priceFullTotal);
			modelAndView.setViewName("ViewCart");
		}
		else {
			
			MessageResponse response =new MessageResponse("No Items in the cart.");
			modelAndView.addObject("ErrorMessage", response);	
			modelAndView.setViewName("ViewCart");
			
		}	
		
		return modelAndView;

	}

	@GetMapping("/viewOrder/{id}")
	public ModelAndView viewOrderDetailsUser(@PathVariable Long id) {
		List<Order> orders1 = orderService.viewOrderDetailsUser(id);
		
		ModelAndView modelAndView = new ModelAndView();
		if (orders1.size() != 0) {
			int totalPrice = 0;
			int priceFullTotal = 0;
			for (int i = 0; i < orders1.size(); i++) {
			    Order order1 = orders1.get(i);	
				int itemPrice = Integer.parseInt(order1.getPrice());
				int quantity = Integer.parseInt(order1.getQuantity());
				totalPrice = itemPrice * quantity;
				priceFullTotal = priceFullTotal + totalPrice;
			}
			
			modelAndView.addObject("ordersO", orders1);
			modelAndView.addObject("totalPrice", priceFullTotal);
			modelAndView.setViewName("OrderDetails");
		} else {
			
			MessageResponse message = new MessageResponse("User list is empty.");
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.setViewName("OrderDetails");
		}

		return modelAndView;

	}

	@RequestMapping("/orderConfirmation/{totalPrice}")
	public ModelAndView orderPage(@PathVariable int totalPrice) {
		int deliveryFee = 250;
		int totalFee = deliveryFee + totalPrice;
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("totalPrice", totalPrice);
		modelAndView.addObject("deliveryFee", deliveryFee);
		modelAndView.addObject("totalFee", totalFee);
		modelAndView.setViewName("Order");
		return modelAndView;
	}

	@GetMapping("/deletecartItem/{id}/{userId}")
	public ModelAndView deleteCartItem(@PathVariable("id") Long id,@PathVariable("userId") Long userId) {
		System.out.println("user id"+userId);
		MessageResponse message=orderService.deleteItem(id);
		ModelAndView modelAndView = new ModelAndView();
        List<Order> orders = orderService.viewCartDetailsUser(userId);
		int totalPrice = 0;
		int priceFullTotal = 0;
		if(orders.size() != 0) {
			for (int i = 0; i < orders.size(); i++) {
				Order order1 = orders.get(i);
				
				int itemPrice = Integer.parseInt(order1.getPrice());
				int quantity = Integer.parseInt(order1.getQuantity());
				totalPrice = itemPrice * quantity;
				priceFullTotal = priceFullTotal + totalPrice;
				

			}
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.addObject("orders", orders);
			
			modelAndView.addObject("totalPrice", priceFullTotal);
			modelAndView.setViewName("ViewCart");
		}
		else {
			
			MessageResponse response =new MessageResponse("No Items in the cart.");
			modelAndView.addObject("ErrorMessage", response);	
			modelAndView.setViewName("ViewCart");
			
		}
		
		
		return modelAndView;



	}

}
