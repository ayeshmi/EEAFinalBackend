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
import com.example.demo.model.Item;
import com.example.demo.service.CommentServiceImpl;
import com.example.demo.service.ItemServiceImpl;

@Controller
@RequestMapping("/api/auth")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentService;
	
	@Autowired
	private ItemServiceImpl itemService;
	
	//adding new book
	@GetMapping("/addComment/{itemId}")
	public ModelAndView addComment(@RequestParam("commentDetail") String commentdetail,@PathVariable("itemId") Long id,@RequestParam("clientEmail") String clientEmail) {
		
		commentService.addComment(commentdetail,java.time.LocalDate.now(),id,clientEmail);

		ModelAndView modelAndView = new ModelAndView();
		Item item = itemService.viewItemByID(id);
		List<Comment> viewComments = commentService.getCommentByItemId(id);
		modelAndView.addObject("item", item);
		modelAndView.addObject("comments", viewComments);
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
