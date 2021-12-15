package com.example.demo.dto;


import com.example.demo.model.Item;

public class Commentdto {
	
	private Long commentID;
	
	private String username;
	
	private String commentDetails;
	
	private String date;

	private Long itemID;
	
	
    private Item item;

	public Commentdto() {
		
	}


	public Long getCommentID() {
		return commentID;
	}

	public void setCommentID(Long commentID) {
		this.commentID = commentID;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getCommentDetails() {
		return commentDetails;
	}

	public void setCommentDetails(String commentDetails) {
		this.commentDetails = commentDetails;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public Long getItemID() {
		return itemID;
	}



	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}

}
