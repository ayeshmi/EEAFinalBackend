package com.example.demo.controller.webController;


import java.time.format.DateTimeFormatter;
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

import com.example.demo.model.Attendence;
import com.example.demo.model.Item;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.service.ItemServiceImpl;
import com.example.demo.service.OrderServiceImpl;
import com.example.demo.service.PharmacientServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;
	
	@Autowired
	private PharmacientServiceImpl pharmacientService;

	@Autowired
	private ItemServiceImpl itemService;

	@PostMapping("/addToCart/{itemId}")
	public ModelAndView addOrder(@RequestParam("clientEmail") String clientEmail, @RequestParam("userId") Long userId,
			@RequestParam("price") String price, @RequestParam("quantity") String quantity, @PathVariable Long itemId,
			@RequestParam("itemName12") String itemName, @RequestParam("image") String image) {
		MessageResponse response = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		      
		response = orderService.addToCartItem(clientEmail, userId, price, quantity, itemId, java.time.LocalDate.now(),
				itemName, image);
		Item item = itemService.viewItemByID(itemId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", item);
		if (response != null) {

			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewSelectedItemDetails");

		} else {
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
	public ModelAndView orderCompleted(@PathVariable("itemId") Long itemId, @PathVariable("userId") Long userId) {

		MessageResponse response = null;
		ModelAndView modelAndView = new ModelAndView();
		response = orderService.orderCompleted(itemId);
		List<Order> orders1 = orderService.viewOrderDetailsUser(userId);
		if (response != null) {
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
		} else {
			MessageResponse message = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.setViewName("OrderDetails");
		}

		return modelAndView;

	}

	@GetMapping("/OrderCancelation")
	public ModelAndView orderCancelation(@RequestParam("deleteId") Long itemId, @RequestParam("userId") Long userId,@RequestParam("reason")String reason) {
		MessageResponse response = null;
		ModelAndView modelAndView = new ModelAndView();
		response = orderService.orderCancelation(itemId,reason);
		List<Order> orders1 = orderService.viewOrderDetailsUser(userId);
		if (response != null) {
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
		} else {
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
		if (orders.size() != 0) {
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
		} else {

			MessageResponse response = new MessageResponse("No Items in the cart.");
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
	public ModelAndView deleteCartItem(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
		System.out.println("user id" + userId);
		MessageResponse message = orderService.deleteItem(id);
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orders = orderService.viewCartDetailsUser(userId);
		int totalPrice = 0;
		int priceFullTotal = 0;
		if (orders.size() != 0) {
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
		} else {

			MessageResponse response = new MessageResponse("No Items in the cart.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewCart");
		}

		return modelAndView;

	}
	
	@GetMapping("/viewCancelOrders")
	public ModelAndView viewCancleOrders() {
		// System.out.println("get item details"+file);
		List<Order> orders = orderService.getAllCancelOrders();
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(" array size is"+orders);
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.setViewName("ViewCancelOrders");	
		}else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewCancelOrders");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/viewCompletedOrders")
	public ModelAndView viewCompletedOrders() {
		// System.out.println("get item details"+file);
		List<Order> orders = orderService.getAllCompletedOrders();
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(" array size is"+orders);
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.setViewName("ViewCompletedOrders");	
		}else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewCompletedOrders");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/viewPendingOrders")
	public ModelAndView viewPendingOrders() {
		// System.out.println("get item details"+file);
		List<Order> orders = orderService.getAllProcessingOrders();
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(" array size is"+orders);
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.setViewName("ViewProcessingOrders");	
		}else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewProcessingOrders");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/assignOrders")
	public ModelAndView viewOrders() {
		// System.out.println("get item details"+file);
		List<Order> orders = orderService.viewOrders();
		List<Attendence> pharmacist=pharmacientService.getAvailablePharmacist(java.time.LocalDate.now().toString());
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(" array size is"+orders);
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.addObject("pharmacist", pharmacist);
			modelAndView.setViewName("AssignOrders");	
		}else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("AssignOrders");
		}
		
		return modelAndView;
	}
	
	@PostMapping("/assignOrderToPharmacist")
	public ModelAndView assignOrderToPharmacist(@RequestParam("name") String name,@RequestParam("deleteId") Long id) {
		System.out.println("sdsd"+name);
		System.out.println("sdsdfff"+id);
		MessageResponse message=orderService.assignOrderToPharmacist(name,id);
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orders = orderService.viewOrders();
		List<Attendence> pharmacist=pharmacientService.getAvailablePharmacist(java.time.LocalDate.now().toString());
		
		System.out.println(" array size is"+orders);
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.addObject("pharmacist", pharmacist);
			modelAndView.setViewName("AssignOrders");	
		}else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("AssignOrders");
		}
		modelAndView.addObject("ErrorMessage", message);
		modelAndView.setViewName("AssignOrders");
		return modelAndView;
	}
	
	@GetMapping("/assignOrders/{date}")
	public ModelAndView viewItemsForOrder(@PathVariable("date") String date) {
		
		List<Order> orders = orderService.viewItemsForOrder(date);
		
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(" array size is"+orders);
		if(orders.size()!=0) {
			modelAndView.addObject("orders", orders);
			
			modelAndView.setViewName("viewItemsForOrder");	
		}else {
			MessageResponse response =new MessageResponse("Item list is empty.");
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("viewItemsForOrder");
		}
		
		return modelAndView;
	}
	
	
	@GetMapping("/pharmacistConfirmation/{id}/{date}")
	public ModelAndView pharmacistConfirmation(@PathVariable("id") Long id,@PathVariable("date") String date) {

		MessageResponse message = orderService.pharmacistConfirmation(id);
		List<Order> orders = orderService.viewItemsForOrder(date);
		ModelAndView modelAndView = new ModelAndView();
		if (message != null) {
			modelAndView.addObject("ErrorMessage", message);
			modelAndView.addObject("orders", orders);
			modelAndView.setViewName("viewItemsForOrder");
		} else {
			MessageResponse messageError = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("ErrorMessage", messageError);
			modelAndView.addObject("orders", orders);
			modelAndView.setViewName("viewItemsForOrder");
		}

		return modelAndView;
	}
	

}
