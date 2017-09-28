package com.beatus.billlive.sendsms.model;

public class Product  extends BaseData implements Comparable<Product>{
	private int productId;
	private String productName;
	private byte[] productImage;
	private String productImageString;
	private String productCategory;
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

	@Override
	public int compareTo(Product compare) {
		return this.productName.compareTo(compare.productName);
	}

	public String getProductImageString() {
		return productImageString;
	}

	public void setProductImageString(String productImageString) {
		this.productImageString = productImageString;
	}	
}
