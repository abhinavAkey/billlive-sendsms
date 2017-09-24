package com.beatus.billlive.sendsms.model;

import java.util.List;

public class ProductWithLocationsAndPricesRequest {

	private List<String> productNameLocAndPrice;

	public List<String> getProductNameLocAndPrice() {
		return productNameLocAndPrice;
	}

	public void setProductNameLocAndPrice(List<String> productNameLocAndPrice) {
		this.productNameLocAndPrice = productNameLocAndPrice;
	}
}
