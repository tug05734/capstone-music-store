package com.capstone.MusicStore.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private int productID;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_condition")
	private String condition;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="img_path")
	private String imagePath;
	
//	@ManyToOne
//	@JoinColumn(name = "")
//	private Category category;
	
//	@ManyToOne
//	@JoinColumn(name = "")
//	private Genre genre;
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}