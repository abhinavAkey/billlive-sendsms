package com.beatus.billlive.sendsms.model;

import java.io.File;
import java.util.Arrays;

public class Product implements Comparable<Product>{

	private String productName;
	private String productPrice;
	private byte[] productImage;
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
		
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice=" + productPrice + ", productImage="
				+ productImage + ", productCategory=" + productCategory + ", productLocation="
				+ productLocation + "]";
	}
	
	
}
