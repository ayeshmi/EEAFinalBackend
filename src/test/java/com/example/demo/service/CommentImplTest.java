package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Comment;
import com.example.demo.model.Item;
import com.example.demo.repository.CommentRepository;

@SpringBootTest
public class CommentImplTest {
	@Autowired
	CommentService commentService;
	
	@MockBean
	CommentRepository commentRepository;
	
	@Test
	@DisplayName("Test save")
	void testSave() {
		// Setup our mock repository
		Comment comment = new Comment();
		doReturn(comment).when(commentRepository).save(any());

		// Execute the service call
		commentService.addComment(comment);

	}
	
	@Test
	@DisplayName("Test getCommentByItemId")
	void testGetCommentByItemId() {
        Item item=new Item();
		Comment comment1 = new Comment(1l, "ayeshmi", "This product is good.", "2022-01-09", item);
		Comment comment2 = new Comment(2l, "ayeshmi", "This product is good.", "2022-01-09", item);
		doReturn(Arrays.asList(comment1, comment2)).when(commentRepository).findByItemID(item.getId());

		// Execute the service call
		List<Comment> comments = commentService.getCommentByItemId(item.getId());

		// Assert the response
		Assertions.assertEquals(2, comments.size(), "findAll should return 2 widgets");

	}
	
}
