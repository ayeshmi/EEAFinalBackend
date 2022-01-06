package com.example.demo.service;

import com.example.demo.dto.MessageResponse;


public interface PaymentService {
	public MessageResponse addPayment(int price, String email, int deliveryFee, int totalFee);
}
