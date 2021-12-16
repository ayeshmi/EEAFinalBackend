package com.example.demo.controller.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.ContactUs;
import com.example.demo.model.Payment;
import com.example.demo.service.PaymentServiceImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class PaymentController {
	
	@Autowired
	private PaymentServiceImpl paymentService;
	
	@GetMapping("/addPayment")
	public ModelAndView addPayment(@ModelAttribute Payment payment) {
		// System.out.println("get item details"+file);
		paymentService.addPayment(payment);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("AddItem");
       modelAndView.addObject("message", "item is successfully added.");
		 return modelAndView;

	}

}
