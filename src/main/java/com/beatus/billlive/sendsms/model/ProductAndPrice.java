package com.beatus.billlive.sendsms.model;

public class ProductAndPrice implements Comparable<ProductAndPrice>{

	private String productName;
	private String price;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public int compareTo(ProductAndPrice compare) {
		return this.productName.compareTo(compare.productName);
	}

}
