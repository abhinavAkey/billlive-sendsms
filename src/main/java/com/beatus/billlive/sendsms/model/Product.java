package com.beatus.billlive.sendsms.model;

import java.io.InputStream;


public class Product implements Comparable<Product>{
	private int productId;
	private String productName;
	private String productPrice;
	private byte[] productImage;
	private String productCategory;
	private String productLocation;
	private int productLocationId;
	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



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



	public byte[] getProductImage() {
		return productImage;
	}



	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
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



	public int getProductLocationId() {
		return productLocationId;
	}



	public void setProductLocationId(int productLocationId) {
		this.productLocationId = productLocationId;
	}



	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice=" + productPrice + ", productImage="
				+ productImage + ", productCategory=" + productCategory + ", productLocation="
				+ productLocation + "]";
	}



	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
