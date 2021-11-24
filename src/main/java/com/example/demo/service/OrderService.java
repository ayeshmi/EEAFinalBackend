package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	public void addToCartItem(String clientEmail, Long userId, String price, String quantity, Long itemId,
			LocalDate date) {
		// TODO Auto-generated method stub
		Order order=new Order();
		order.setClientEmail(clientEmail);
		order.setDate(date.toString());
		order.setItemId(itemId);
		order.setPrice(price);
		order.setQuantity(quantity);
		order.setUserId(userId);
		order.setType("cart");
		
		orderRepository.save(order);
		
	}

	public List<Order> viewCartDetailsUser(Long id){
		List<Order> orders=orderRepository.findByUserId(id);
		
		return orders;
	}

	public void orderConfirmation(String email) {
		System.out.println("sdsd"+email);
		List<Order> orders=orderRepository.findByClientEmail(email);
		for(int i=0;i<orders.size();i++) {
			Order order=orders.get(i);
			order.setType("order");
			order.setStatus("processing");
			orderRepository.save(order);
		}
		
	}

	public void deleteItem(Long id) {
		// TODO Auto-generated method stub
		Order order=orderRepository.findById(id)
				.orElseThrow();
		orderRepository.delete(order);
		System.out.println("order is deleted");
	}
	
	

	
	
	
	
	

}
