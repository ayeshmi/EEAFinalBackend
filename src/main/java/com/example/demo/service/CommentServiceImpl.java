package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.Item;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class CommentServiceImpl implements CommentService{


	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public void addComment(Comment comment) {
		  
		commentRepository.save(comment);
		
	}
	
	

	@Override
	public List<Comment> getCommentByItemId(Long id){
		List<Comment>  comments=null;
		try {
			comments=commentRepository.findByItemID(id);
		}catch(Exception e) {
			e.printStackTrace();
		}			
		
		return  comments;
	}

	@Override
	public void addComment(String commentdetail, LocalDate date, Long id, String clientEmail) {
		Comment comment=new Comment();
		Item item=itemRepository.findById(id).orElseThrow();
		comment.setCommentDetails(commentdetail);
		comment.setDate(date.toString());
		comment.setItemID(id);
		comment.setUsername(clientEmail);
		comment.setItem(item);
		commentRepository.save(comment);
		System.out.println("Comment is sucessfully added");
		
		
	}
	
}
