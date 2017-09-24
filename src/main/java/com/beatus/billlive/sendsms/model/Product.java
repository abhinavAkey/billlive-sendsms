package com.beatus.billlive.sendsms.model;

public class Product implements Comparable<Product>{

	private String productName;
	private String productPrice;
	private String productCategory;
	private String productLocation;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductLocation() {
		return productLocation;
	}
	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}
	@Override
	public int compareTo(Product compare) {
		return this.productName.compareTo(compare.productName);
	}
}
