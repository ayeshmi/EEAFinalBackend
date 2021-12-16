package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailSender;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	

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
    		emailSender.sendEmailOrderCompleted();
    		message=new MessageResponse("We are happy, Order is successfully completed.");
        }catch(Exception e) {
        	System.out.println("Error is" + e);
        }
		return message;

	}

	@Override
	public MessageResponse orderCancelation(Long itemId) {
		Order order=null;
		MessageResponse message=null;
		try {
			order = orderRepository.findById(itemId).orElseThrow();
			order.setStatus("Cancel");
			orderRepository.save(order);
			emailSender.sendEmailOrderCancelation();
			message=new MessageResponse("Order is canceled.");
		}catch(Exception e) {
			System.out.println("Error is" + e);
		
		
	}
		return message;
	}
	

	

}
