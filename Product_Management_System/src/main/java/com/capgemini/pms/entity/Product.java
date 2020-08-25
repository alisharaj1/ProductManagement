package com.capgemini.pms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "product_table")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="productId")
	@SequenceGenerator(name="productId",sequenceName="productId",allocationSize=50)
	@Column(name="productId")
	@NotNull(message="product id must not be null")
	@Positive
	private int productId;
	@Column(name="price")
	@NotNull(message="product price must not be null")
	@Positive
	private double price;
	@Column(name="colour")
	@NotEmpty(message="colour should be there")
	private String colour;
	@Column(name="dimension")
	@NotEmpty(message="dimension can not be empty")
	private String dimension;
	@Column(name="specification")
	@NotEmpty(message="Specification not be empty")
	private String specification;
	@Column(name="manufacturer")
	@NotEmpty(message="Manufacturer not be Empty")
	private String manufacturer;
	@Column(name="quantity")
	@NotNull(message="quantity must not be null")
	@Positive
	private int quantity;
	@Column(name="productCategory")
	@NotEmpty(message="productCategory not be Empty")
	private String productCategory;
	@Column(name="productName")
	@NotEmpty(message="Productname not be Empty")
	private String productName;

	
	public Product() {}

	

public Product(int productId, double price, String colour, String dimension, String specification,
			String manufacturer, int quantity, String productCategory, String productName) {
		super();
		this.productId = productId;
		this.price = price;
		this.colour = colour;
		this.dimension = dimension;
		this.specification = specification;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.productCategory = productCategory;
		this.productName = productName;
	}
	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}






	
	
	

}

