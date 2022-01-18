package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Comment;

public interface CommentService {
	public void addComment(Comment comment);
	public List<Comment> getCommentByItemId(Long id);
	public void addComment(String commentdetail, LocalDate date, Long id, String clientEmail);
}
