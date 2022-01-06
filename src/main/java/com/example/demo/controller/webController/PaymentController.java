package com.example.demo.controller.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.PaymentServiceImpl;
import com.example.demo.dto.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class PaymentController {
	
	@Autowired
	private PaymentServiceImpl paymentService;
	
	@GetMapping("/addPayment/{price}/{deliveryFee}/{totalFee}/{email}")
	public ModelAndView addPayment(@PathVariable("price") int price,@PathVariable("email") String email,@PathVariable("deliveryFee") int deliveryFee,@PathVariable("totalFee") int totalFee) {
		 
		MessageResponse message=paymentService.addPayment(price,email,deliveryFee,totalFee);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("AddItem");
       modelAndView.addObject("message", "item is successfully added.");
		 return modelAndView;

	}
	
	

}
