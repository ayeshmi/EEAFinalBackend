package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Order;

public interface OrderService {
	public MessageResponse addToCartItem(String clientEmail, Long userId, String price, String quantity, Long itemId,
			LocalDate date, String itemName,String image);
	public MessageResponse addToCartItemAPI(String clientEmail, Long userId, String price, String quantity, Long itemId,
			LocalDate date, String itemName,String  image,String imageName);
	public List<Order> viewCartDetailsUser(Long id);
	public List<Order> viewPendingOrders(Long id);
	public List<Order> viewCancelOrders(Long id);
	public List<Order> viewComletedOrders(Long id);
	public void orderConfirmation(String email);
	public MessageResponse deleteItem(Long id);
	public List<Order> viewOrderDetailsUser(Long id);
	public MessageResponse orderCompleted(Long itemId);
	public MessageResponse orderCancelation(Long itemId,String reason);
	public List<Order> getAllCancelOrders();
	public List<Order> getAllCompletedOrders();
	public List<Order> getAllProcessingOrders();
	public List<Order> viewOrders();
	public MessageResponse assignOrderToPharmacist(String name, Long id);
	public List<Order> viewItemsForOrder(String date);
	public MessageResponse pharmacistConfirmation(Long id);
	String imageUploader(MultipartFile file);

}
