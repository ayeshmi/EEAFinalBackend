package com.example.demo.dto;


public class Userdto {
	
	private Long id;

	
	private String username;

	private String email;

	private String birthday;

	private String address;

	private String password;
	
	
	private String image;
	
	private String imageName;
    
    
	public Userdto() {
	}





	public Userdto(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	
	}
	
	public Userdto(String username, String password) {
		this.username = username;
		
		this.password = password;
	
	}
		
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
}
