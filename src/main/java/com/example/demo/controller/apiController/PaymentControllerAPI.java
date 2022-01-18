package com.example.demo.controller.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.OrderServiceImpl;
import com.example.demo.service.PaymentServiceImpl;
import com.example.demo.dto.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class PaymentControllerAPI {
	
	@Autowired
	private PaymentServiceImpl paymentService;
	
	@Autowired
	private OrderServiceImpl orderService;
	
	//add new payment
	@GetMapping("/addPaymentRA/{price}/{deliveryFee}/{totalFee}/{email}")
	public ResponseEntity<Object> addPayment(@PathVariable("price") int price,@PathVariable("email") String email,@PathVariable("deliveryFee") int deliveryFee,@PathVariable("totalFee") int totalFee) {
		 
		orderService.orderConfirmation(email);
		MessageResponse message=paymentService.addPayment(price,email,deliveryFee,totalFee);

		return ResponseEntity.ok(message);
		

	}
	
	

}
