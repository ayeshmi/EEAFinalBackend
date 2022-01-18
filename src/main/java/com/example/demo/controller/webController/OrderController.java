package com.example.demo.controller.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	//add item to cart by ID
	@PostMapping("/addToCart/{itemId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView addOrder(@RequestParam("clientEmail") String clientEmail, @RequestParam("userId") Long userId,
			@RequestParam("price") String price, @RequestParam("quantity") String quantity, @PathVariable Long itemId,
			@RequestParam("itemName12") String itemName, @RequestParam("image") String image) {
		
		MessageResponse response = null;
		ModelAndView modelAndView = new ModelAndView(); 
		
		response = orderService.addToCartItem(clientEmail, userId, price, quantity, itemId, java.time.LocalDate.now(),
				itemName, image);
		
		Item item = itemService.viewItemByID(itemId);
			
		modelAndView.addObject("item", item);
		if (response != null) {
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("ViewSelectedItemDetails");

		} else {
			MessageResponse response1 = new MessageResponse("Something went wrong, try again.");
			modelAndView.addObject("errorMsg", response1);
			modelAndView.setViewName("ViewSelectedItemDetails");

		}

		return modelAndView;

	}

	//add order by prescription
	@PostMapping("/addOrderByPrescription")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView addOrderByPrescription(@RequestParam("email") String clientEmail, @RequestParam("id") Long userId,
			@RequestParam("name") String name, @RequestParam("note") String note,  @RequestParam("image") MultipartFile image) {
		MessageResponse response = null;
		
		ModelAndView modelAndView = new ModelAndView();
	      
		response = orderService.addOrderByPrescription(clientEmail, userId, name, note, java.time.LocalDate.now(),
				 image);
		if(response != null) {
			modelAndView.addObject("ErrorMessage", response);
			modelAndView.setViewName("AddOrderByMedical Prescription");	
		}else {
			MessageResponse message =new MessageResponse("Check inputs and try again!");
			modelAndView.addObject("errorMsg", message);
		}
		
		return modelAndView;

	}
	

	@GetMapping("/OrderCompleted/{itemId}/{userId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
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
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
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
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView viewCartDetailsUser(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orders = orderService.viewCartDetailsUser(id);

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
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewCart");

		}

		return modelAndView;

	}
	
	@GetMapping("/addOrder/{email}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public void orderConfirmation(@PathVariable String email) {
		
		orderService.orderConfirmation(email);
		

	}

	@GetMapping("/viewOrder/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
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
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
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
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView deleteCartItem(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
		
		ModelAndView modelAndView = new ModelAndView();
		int totalPrice = 0;
		int priceFullTotal = 0;
		
		MessageResponse message = orderService.deleteItem(id);
		
		List<Order> orders = orderService.viewCartDetailsUser(userId);
		
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
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView viewCancleOrders() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Order> orders = orderService.getAllCancelOrders();
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.setViewName("ViewCancelOrders");	
		}else {
			MessageResponse response =new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewCancelOrders");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/viewCompletedOrders")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView viewCompletedOrders() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Order> orders = orderService.getAllCompletedOrders();
	
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.setViewName("ViewCompletedOrders");	
		}else {
			MessageResponse response =new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewCompletedOrders");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/viewPendingOrders")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ModelAndView viewPendingOrders() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Order> orders = orderService.getAllProcessingOrders();
		
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.setViewName("ViewProcessingOrders");	
		}else {
			MessageResponse response =new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("ViewProcessingOrders");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/assignOrders")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST','ROLE_USER')")
	public ModelAndView viewOrders() {
		
		List<Order> orders = orderService.viewOrders();
		List<Attendence> pharmacist=pharmacientService.getAvailablePharmacist(java.time.LocalDate.now().toString());
		ModelAndView modelAndView = new ModelAndView();
		
		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.addObject("pharmacist", pharmacist);
			modelAndView.setViewName("AssignOrders");	
		}else {
			MessageResponse response =new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("AssignOrders");
		}
		
		return modelAndView;
	}
	
	@PostMapping("/assignOrderToPharmacist")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView assignOrderToPharmacist(@RequestParam("name") String name,@RequestParam("deleteId") Long id) {
		
		ModelAndView modelAndView = new ModelAndView();
		MessageResponse message=orderService.assignOrderToPharmacist(name,id);
		List<Order> orders = orderService.viewOrders();
		List<Attendence> pharmacist=pharmacientService.getAvailablePharmacist(java.time.LocalDate.now().toString());

		if(orders.size()!=0) {
			modelAndView.addObject("items", orders);
			modelAndView.addObject("pharmacist", pharmacist);
			modelAndView.setViewName("AssignOrders");	
		}else {
			MessageResponse response =new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("AssignOrders");
		}
		modelAndView.addObject("ErrorMessage", message);
		modelAndView.setViewName("AssignOrders");
		return modelAndView;
	}
	
	@GetMapping("/assignOrders/{date}")
	@PostMapping("/assignOrderToPharmacist")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
	public ModelAndView viewItemsForOrder(@PathVariable("date") String date) {
		
		List<Order> orders = orderService.viewItemsForOrder(date);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(orders.size()!=0) {
			modelAndView.addObject("orders", orders);
			modelAndView.setViewName("viewItemsForOrder");	
		}else {
			MessageResponse response =new MessageResponse("No machers found!");
			modelAndView.addObject("errorMsg", response);
			modelAndView.setViewName("viewItemsForOrder");
		}
		
		return modelAndView;
	}
	
	
	@GetMapping("/pharmacistConfirmation/{id}/{date}")
	@PostMapping("/assignOrderToPharmacist")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PHARMACIST')")
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
