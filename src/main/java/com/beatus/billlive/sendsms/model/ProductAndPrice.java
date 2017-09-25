package com.beatus.billlive.sendsms.model;

public class ProductAndPrice implements Comparable<ProductAndPrice>{

	private String productName;
	private double price;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	@Override
	public int compareTo(ProductAndPrice compare) {
		return this.productName.compareTo(compare.productName);
	}

}
