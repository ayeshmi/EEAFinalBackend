package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Payment;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepo;
	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.save(payment);
		
		
	}
	
	

}
