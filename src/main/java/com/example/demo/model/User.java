package com.example.demo.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(max = 100)
	@Email
	private String email;

	
	@Size(max = 50)
	private String birthday;
	
	@Size(max = 200)
	private String address;
	
	@NotBlank
	@Size(max = 120)
	private String password;
	
	
	@Size(max = 250)
	private String image;
	
	@Size(max = 250)
	private String imageName;
    


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
    
	public User() {
	}


	public User(@Size(max = 50) String username, @NotBlank @Size(max = 100) @Email String email,
			@Size(max = 50) String birthday, @Size(max = 200) String address, @Size(max = 250) String image) {
		super();
		this.username = username;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
		this.image = image;
	}


	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	
	}
	
	public User(String username, String password) {
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	

	


	
}
