package com.example.demo.model;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="contactUs")
public class ContactUs {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank
@Size(max = 60)
private String name;
@NotBlank
@Size(max = 60)
private String email;
@NotBlank
@Size(max = 200)
private String message;

@Size(max = 200)
private String answer;

@JsonIgnore
@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE
        , CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name = "user_id")
private User user;




public ContactUs(Long id,  String name,  String email, String message) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.message = message;
}

public ContactUs(Long id,  String name,  String email, String message,String answer) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.message = message;
	this.answer=answer;
}

public ContactUs()
{
	
}



public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

public String getAnswer() {
	return answer;
}

public void setAnswer(String answer) {
	this.answer = answer;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}





}
