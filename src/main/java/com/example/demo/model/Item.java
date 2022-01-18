package com.example.demo.model;


import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="items")
public class Item {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Size(max = 60)
	private String name;
	
	
	
	@Size(max = 20)
	private String availability;
	
	@NotBlank
	@Size(max = 50)
	private String price;
	
	@NotBlank
	@Size(max = 700)
	private String description;
	

	@Size(max = 700)
	private String specifications;
	
	@NotBlank
	@Size(max = 700)
	private String suitableFor;
	
	@NotBlank
	@Size(max = 700)
	private String howToUse;
	
	@NotBlank
	@Size(max = 700)
	private String ingredients;
		
	@Size(max = 50)
	private String delivery;
		
	@Size(max = 50)
	private String returnItem;
		
	@Size(max = 50)
	private String itemType;
	
	@Size(max = 750)
	private String image;
	
	@Size(max = 250)
	private String imageName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REFRESH})
	 private List<Comment> comments;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE
                    , CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;
	
	public Item()
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

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getSuitableFor() {
		return suitableFor;
	}

	public void setSuitableFor(String suitableFor) {
		this.suitableFor = suitableFor;
	}

	public String getHowToUse() {
		return howToUse;
	}

	public void setHowToUse(String howToUse) {
		this.howToUse = howToUse;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getReturnItem() {
		return returnItem;
	}

	public void setReturnItem(String returnItem) {
		this.returnItem = returnItem;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
}
