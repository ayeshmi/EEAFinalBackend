package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Attendence;
import com.example.demo.model.EmailSender;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.model.Pharmacient;
import com.example.demo.model.User;
import com.example.demo.repository.AttendenceRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PharmacientRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PharmacientRepository pharmacientRepository;
	

	@Autowired
	private EmailSender emailSender;

	@Override
	public MessageResponse addToCartItem(String clientEmail, Long userId, String price, String quantity, Long itemId,
			LocalDate date, String itemName,String image) {
		// TODO Auto-generated method stub
		Order order = new Order();
		int itemQuantity=Integer.parseInt(quantity);
		int itemPrice=Integer.parseInt(price);
		int totalPrice=itemQuantity*itemPrice;
		User user=userRepository.findById(userId).orElseThrow();
		MessageResponse message=null;
		try {
			order.setClientEmail(clientEmail);
			order.setDate(date.toString());
			order.setItemId(itemId);
			order.setPrice(price);
			
			order.setQuantity(quantity);
			//order.setUserId(userId);
			order.setType("cart");
			order.setName(itemName);
			order.setTotalPrice(Integer.toString(totalPrice));
			order.setItemImage(image);
			order.setUser(user);

			orderRepository.save(order);
			message=new MessageResponse("Item is successfully added to the cart.");
		}catch(Exception e) {
			System.out.println("Error is" + e);
		}
		return message;
		

	}
	
	@Override
	public MessageResponse addToCartItemAPI(String clientEmail, Long userId, String price, String quantity, Long itemId,
			LocalDate date, String itemName,String  image,String imageName) {
		// TODO Auto-generated method stub
		Order order = new Order();
		int itemQuantity=Integer.parseInt(quantity);
		int itemPrice=Integer.parseInt(price);
		int totalPrice=itemQuantity*itemPrice;
		User user=userRepository.findById(userId).orElseThrow();
		MessageResponse message=null;
		try {
			
			
			order.setClientEmail(clientEmail);
			order.setDate(date.toString());
			order.setItemId(itemId);
			order.setPrice(price);
			order.setImageName(imageName);
			order.setQuantity(quantity);
			//order.setUserId(userId);
			order.setUser(user);
			order.setType("cart");
			order.setName(itemName);
			order.setTotalPrice(Integer.toString(totalPrice));
			order.setItemImage(image);

			orderRepository.save(order);
			message=new MessageResponse("Item is successfully added to the cart.");
		}
		
		catch(Exception e) {
			System.out.println("Error is" + e);
		}
		return message;
		

	}

	@Override
	public List<Order> viewCartDetailsUser(Long id) {
		List<Order> orders=null;
		try {
			orders = orderRepository.searchCartDetails(id);
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}

		return orders;
	}
	
	@Override
	public List<Order> viewPendingOrders(Long id) {
		List<Order> orders=null;
		try {
			orders = orderRepository.searchProcessOrderDetails(id);
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}

		return orders;
	}
	
	@Override
	public List<Order> viewCancelOrders(Long id) {
		List<Order> orders=null;
		try {
			orders = orderRepository.searchCancelOrderDetails(id);
			System.out.println("array size ishhh"+orders.size());
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}

		return orders;
	}
	
	@Override
	public List<Order> viewComletedOrders(Long id) {
		List<Order> orders=null;
		try {
			orders = orderRepository.searchComletedOrderDetails(id);
		}catch(Exception e)
		{
			System.out.println("Error is" + e);
		}

		return orders;
	}

	@Override
	public void orderConfirmation(String email) {
		List<Order> orders=null;
		try {
			orders = orderRepository.findByClientEmail(email);
			for (int i = 0; i < orders.size(); i++) {
				Order order = orders.get(i);
				order.setType("order");
				order.setStatus("processing");
				orderRepository.save(order);
			}
			
		}catch(Exception e) {
			System.out.println("Error is" + e);
		}
		

	}

	@Override
	public MessageResponse deleteItem(Long id) {
		MessageResponse message=null;
		try {
			Order order = orderRepository.findById(id).orElseThrow();
			orderRepository.delete(order);
			
			message=new MessageResponse ("Item is successfully deleted from the cart.");
		}catch(Exception e) {
			System.out.println("Error is" + e);
		}
		
		return message;
	}

	@Override
	public List<Order> viewOrderDetailsUser(Long id) {
		// TODO Auto-generated method stub
		List<Order> orders = null;
		try {
			orders = orderRepository.search(id);
			
		} catch (Exception e) {
			System.out.println("Error is" + e);
		}

		return orders;

	}

	@Override
	public MessageResponse orderCompleted(Long itemId) {
		Order order=null;
		MessageResponse message=null;
        try {
        	order = orderRepository.findById(itemId).orElseThrow();
        	order.setStatus("Completed");
    		orderRepository.save(order);
    		order.setCompletedDate(java.time.LocalDate.now().toString());
    		emailSender.sendEmailOrderCompleted();
    		message=new MessageResponse("We are happy, Order is successfully completed.");
        }catch(Exception e) {
        	System.out.println("Error is" + e);
        }
		return message;

	}

	@Override
	public MessageResponse orderCancelation(Long itemId,String reason) {
		Order order=null;
		MessageResponse message=null;
		try {
			order = orderRepository.findById(itemId).orElseThrow();
			order.setStatus("Cancel");
			order.setReason(reason);
			order.setCancellationDate(java.time.LocalDate.now().toString());
			orderRepository.save(order);
			emailSender.sendEmailOrderCancelation();
			message=new MessageResponse("Order is canceled.");
		}catch(Exception e) {
			System.out.println("Error is" + e);
		
		
	}
		return message;
	}

	public List<Order> getAllCancelOrders() {
		List<Order> orders=null;
		try {
			orders=orderRepository.getCancelOrders();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public List<Order> getAllCompletedOrders() {
		List<Order> orders=null;
		try {
			orders=orderRepository.getCompletedOrders();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public List<Order> getAllProcessingOrders() {
		List<Order> orders=null;
		try {
			orders=orderRepository.getProcessingOrders();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public List<Order> viewOrders() {
		List<Order> orders=null;
		try {
			orders=orderRepository.viewOrders();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public MessageResponse assignOrderToPharmacist(String name, Long id) {
		MessageResponse message=null;
		Order order=orderRepository.findById(id).orElseThrow();
		
		Pharmacient pharmacient=pharmacientRepository.findByFirstName(name);
		List<Order> orders=orderRepository.findByDate(order.getDate());
		message=new MessageResponse("Order is assigned to phramacist");
		System.out.println("orders"+pharmacient.getId());
		for(int i=0;i<orders.size();i++) {
			
			orders.get(i).setPharmacist(pharmacient);
			orderRepository.save(orders.get(i));
			System.out.println("hellooo");
			
			
		}
		return message;
		
		
	}

	public List<Order> viewItemsForOrder(String date) {
		List<Order> orders=null;
		try {
			orders=orderRepository.itemListOfOrder(date);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return orders;
	}
	
	public MessageResponse pharmacistConfirmation(Long id) {
		MessageResponse message=new MessageResponse("Order is marked as completed");
		Order order=orderRepository.findById(id).orElseThrow();
		order.setPhramacistConfirmation("Confirm");
		orderRepository.save(order);
		return message;
	}

	

}
