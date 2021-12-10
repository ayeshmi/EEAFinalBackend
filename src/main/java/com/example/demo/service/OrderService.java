package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailSender;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EmailSender emailSender;

	public MessageResponse addToCartItem(String clientEmail, Long userId, String price, String quantity, Long itemId,
			LocalDate date, String itemName) {
		// TODO Auto-generated method stub
		Order order = new Order();
		MessageResponse message=null;
		try {
			order.setClientEmail(clientEmail);
			order.setDate(date.toString());
			order.setItemId(itemId);
			order.setPrice(price);
			order.setQuantity(quantity);
			order.setUserId(userId);
			order.setType("cart");
			order.setItemName(itemName);

			orderRepository.save(order);
			message=new MessageResponse("Item is successfully added to the cart.");
		}catch(Exception e) {
			System.out.println("Error is" + e);
		}
		return message;
		

	}

	public List<Order> viewCartDetailsUser(Long id) {
		List<Order> orders = orderRepository.searchCartDetails(id);
		System.out.println("fsfsf" + orders);

		return orders;
	}

	public void orderConfirmation(String email) {
		System.out.println("sdsd" + email);
		List<Order> orders = orderRepository.findByClientEmail(email);
		for (int i = 0; i < orders.size(); i++) {
			Order order = orders.get(i);
			order.setType("order");
			order.setStatus("processing");
			orderRepository.save(order);
		}

	}

	public void deleteItem(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow();
		orderRepository.delete(order);
		System.out.println("order is deleted");
	}

	public List<Order> viewOrderDetailsUser(Long id) {
		// TODO Auto-generated method stub
		List<Order> orders = null;
		try {
			orders = orderRepository.search(id);
			System.out.println("orders" + orders.size());
		} catch (Exception e) {
			System.out.println("Error is" + e);
		}

		return orders;

	}

	public void orderCompleted(Long itemId) {
		Order order = orderRepository.findById(itemId).orElseThrow();

		order.setStatus("Completed");
		orderRepository.save(order);
		emailSender.sendEmailOrderCompleted();

	}

	public void orderCancelation(Long itemId) {
		Order order = orderRepository.findById(itemId).orElseThrow();
		order.setStatus("Cancel");
		orderRepository.save(order);
		emailSender.sendEmailOrderCancelation();
	}

}
