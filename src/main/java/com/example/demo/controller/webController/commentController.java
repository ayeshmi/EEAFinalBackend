package com.example.demo.controller.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentServiceImpl;

@Controller
@RequestMapping("/api/auth")
public class commentController {

	@Autowired
	private CommentServiceImpl commentService;
	
	//adding new book
	@GetMapping("/addComment/{itemId}")
	public ModelAndView addComment(@RequestParam("commentDetail") String commentdetail,@PathVariable("itemId") Long id,@RequestParam("clientEmail") String clientEmail) {
		
		commentService.addComment(commentdetail,java.time.LocalDate.now(),id,clientEmail);
		//return ResponseEntity.ok(new MessageResponse("Book added successfully!"));
		ModelAndView modelAndView = new ModelAndView();

		
		modelAndView.setViewName("ViewSelectedItemDetails");

		return modelAndView;
	}
	
	@GetMapping("/allCommentByID/{id}")
	public List<Comment> getCommentByID(@PathVariable Long id){
		
	List<Comment> updateConatctUS=commentService.getCommentByItemId(id);
	System.out.println("Hello Comment"+updateConatctUS);
		return updateConatctUS;
	}
	
	
	
}
