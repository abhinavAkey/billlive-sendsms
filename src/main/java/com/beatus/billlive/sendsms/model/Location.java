package com.beatus.billlive.sendsms.model;

public class Location implements Comparable<Location>{

	private int locationId;
	private String locationName;
	private String locationCity;
	private String locationDistrict;
	private String locationState;
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationCity() {
		return locationCity;
	}
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	public String getLocationDistrict() {
		return locationDistrict;
	}
	public void setLocationDistrict(String locationDistrict) {
		this.locationDistrict = locationDistrict;
	}
	public String getLocationState() {
		return locationState;
	}
	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}
	
	@Override
	public int compareTo(Location compare) {
		return this.locationName.compareTo(compare.locationName);
	}
}
