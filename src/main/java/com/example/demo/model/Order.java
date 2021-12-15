package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="orderTable")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_ids")
    private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "pharmacist_id")
    private Pharmacient pharmacist;
	
	 @OneToOne(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	    private Payment payment;
	 
	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {CascadeType.PERSIST, CascadeType.MERGE
	                    , CascadeType.DETACH, CascadeType.REFRESH})
	 @JoinTable(
	            name = "order_item",
	            joinColumns = @JoinColumn(name = "order_id"),
	            inverseJoinColumns = @JoinColumn(name = "item_id")
	    )
	    private List<Item> items;
	
	
	private Long itemId;
	
	@NotBlank
	@Size(max = 200)
	private String clientEmail;
	
	@Size(max = 60)
	private String type;	
	
	@NotBlank
	@Size(max = 15)
	private String date;
	
	@NotBlank
	@Size(max = 100)
	private String price;
	
	@NotBlank
	@Size(max = 100)
	private String quantity;
	
	
	@Size(max = 100)
	private String status;
	
	@Size(max = 200)
	private String name;
	
	@Size(max = 100)
	private String totalPrice;
	
	@Size(max = 300)
	private String itemImage;
	
	@Size(max = 300)
	private String imageName;

	public Order() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String itemName) {
		this.name = itemName;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String itemName) {
		this.imageName = itemName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pharmacient getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacient pharmacist) {
		this.pharmacist = pharmacist;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
	
	

}
