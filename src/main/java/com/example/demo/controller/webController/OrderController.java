package com.example.demo.controller.webController;

import java.util.ArrayList;
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
import com.example.demo.model.MessageResponse;
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
	public ModelAndView addOrder(@RequestParam("clientEmail") String clientEmail,
			@RequestParam("userId") Long userId, @RequestParam("price") String price,
			@RequestParam("quantity") String quantity, @PathVariable Long itemId,
			@RequestParam("itemName") String itemName) {
		MessageResponse response = null;
		response = orderService.addToCartItem(clientEmail, userId, price, quantity, itemId, java.time.LocalDate.now(),
				itemName);
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

	@GetMapping("/OrderCompleted/{itemId}")
	public void orderCompleted(@PathVariable Long itemId) {
		// System.out.println("sdsd"+order.getClientEmail());

		orderService.orderCompleted(itemId);
		System.out.println("order is sucessfully completed");

	}

	@GetMapping("/OrderCancelation/{itemId}")
	public void orderCancelation(@PathVariable Long itemId) {
		// System.out.println("sdsd"+order.getClientEmail());

		orderService.orderCancelation(itemId);
		System.out.println("order is sucessfully completed");

	}

	@GetMapping("/viewCart/{id}")
	public ModelAndView viewCartDetailsUser(@PathVariable Long id) {
		System.out.println("list is empty125");
		List<Order> orders = orderService.viewCartDetailsUser(id);
		List<Item> itemList = new ArrayList<Item>();
		ModelAndView modelAndView = new ModelAndView();
		int totalPrice = 0;
		int priceFullTotal = 0;
		if(orders.size() != 0) {
			for (int i = 0; i < orders.size(); i++) {
				Order order1 = orders.get(i);
				Item item = itemService.viewItemByID(order1.getItemId());
				int itemPrice = Integer.parseInt(order1.getPrice());
				int quantity = Integer.parseInt(order1.getQuantity());
				totalPrice = itemPrice * quantity;
				priceFullTotal = priceFullTotal + totalPrice;
				itemList.add(item);

			}

			modelAndView.addObject("orders", orders);
			modelAndView.addObject("items", itemList);
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
			
			List<Item> itemList1 = new ArrayList<Item>();

			int totalPrice = 0;
			int priceFullTotal = 0;
			for (int i = 0; i < orders1.size(); i++) {
				Order order1 = orders1.get(i);
				Item item = itemService.viewItemByID(order1.getItemId());
				int itemPrice = Integer.parseInt(order1.getPrice());
				int quantity = Integer.parseInt(order1.getQuantity());
				totalPrice = itemPrice * quantity;
				priceFullTotal = priceFullTotal + totalPrice;
				itemList1.add(item);

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

	@GetMapping("/deletecartItem/{id}")
	public void deleteCartItem(@PathVariable Long id) {
		orderService.deleteItem(id);


	}

}
