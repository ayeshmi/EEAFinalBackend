package com.example.demo.controller.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.PaymentService;



@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/addPayment")
	public ModelAndView addPayment(@RequestParam("image") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("specifications") String specifications,
			@RequestParam("price") String price, @RequestParam("ingredients") String ingredients,
			@RequestParam("delivery") String delivery, @RequestParam("suitableFor") String suitableFor,
			@RequestParam("howToUse") String howToUse, @RequestParam("returnItem") String returnItem,@RequestParam("itemType") String itemType) {
		// System.out.println("get item details"+file);
		paymentService.addPayment();

		System.out.println("Request is leanded" + file);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("AddItem");
       modelAndView.addObject("message", "item is successfully added.");
		 return modelAndView;

	}

}
