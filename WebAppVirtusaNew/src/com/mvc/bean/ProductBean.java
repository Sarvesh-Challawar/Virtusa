package com.mvc.bean;

import java.util.Date;

public class ProductBean {

	private int id;
	
	private int vendorcode;
	
	private int threshold;
	
	private String category;
	
	private String description;
	
	private int count;
	
	private double cost;
	
	private String currency;
	
	private Date expirydate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVendorcode() {
		return vendorcode;
	}

	public void setVendorcode(int vendorcode) {
		this.vendorcode = vendorcode;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}
	
}
