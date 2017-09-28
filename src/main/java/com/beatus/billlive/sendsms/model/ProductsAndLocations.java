package com.beatus.billlive.sendsms.model;

public class ProductsAndLocations  extends BaseData{

	private int productLocationId;
	private String productName;
	private String productCategory;
	private int productId;
	private int locationId;
	private String productLocationName;
	private String productPrice;
	
	public int getProductLocationId() {
		return productLocationId;
	}
	public void setProductLocationId(int productLocationId) {
		this.productLocationId = productLocationId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getProductLocationName() {
		return productLocationName;
	}
	public void setProductLocationName(String productLocationName) {
		this.productLocationName = productLocationName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

}
