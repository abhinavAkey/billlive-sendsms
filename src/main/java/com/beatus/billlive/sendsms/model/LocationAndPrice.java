package com.beatus.billlive.sendsms.model;

import java.util.List;

public class LocationAndPrice implements Comparable<LocationAndPrice>{

	private String locationName;
	private List<ProductAndPrice> productAndPrices;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public List<ProductAndPrice> getProductAndPrices() {
		return productAndPrices;
	}
	public void setProductAndPrices(List<ProductAndPrice> productAndPrices) {
		this.productAndPrices = productAndPrices;
	}
	@Override
	public int compareTo(LocationAndPrice compare) {
		return this.locationName.compareTo(compare.locationName);	
	}
}
