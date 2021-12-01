package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {


	@Autowired
	private CommentRepository commentRepository;
	
	public void addComment(Comment comment) {
		  
		commentRepository.save(comment);
		
	}

	public Comment getCommentByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Comment> getCommentByItemId(Long id){
		
		System.out.println("hello ayeshmi"+id);
		List<Comment>  comments=commentRepository.findByItemID(id);
				
		System.out.println("Hello Comment123"+comments);
		return  comments;
	}

	public void addComment(String commentdetail, LocalDate date, Long id, String clientEmail) {
		Comment comment=new Comment();
		comment.setCommentDetails(commentdetail);
		comment.setDate(date.toString());
		comment.setItemID(id);
		comment.setUsername(clientEmail);
		commentRepository.save(comment);
		System.out.println("Comment is sucessfully added");
		
		
	}
	
}
