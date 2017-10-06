package com.beatus.billlive.sendsms.model;

import java.sql.Date;

public class Product  extends BaseData implements Comparable<Product>{
	private int productId;
	private String productName;
	private byte[] productImage;
	private String productImageString;
	private String productCategory;
	private String brandName;
	private String hsnCode;
	private double costPriceExcludingTax;
	private QuantityType quantityType;
	private double quantityReceived;
	private double quantityAvailable;
	private double taxableAmount;
	private double gstTax;
	private double taxAmount;
	private double costPriceInclusiveTax;
	private double marginInPercentage;
	private double marginInRupees;
	private double discountInPercentage;
	private double discountInRupees;
	private double lowinvnt;	
	private double mrpPerUnit;
	private double sellingPrice;	
	private Date expDate;	
	private double totalUnitPrice;	
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public double getCostPriceExcludingTax() {
		return costPriceExcludingTax;
	}

	public void setCostPriceExcludingTax(double costPriceExcludingTax) {
		this.costPriceExcludingTax = costPriceExcludingTax;
	}

	public QuantityType getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(QuantityType quantityType) {
		this.quantityType = quantityType;
	}

	public double getQuantityReceived() {
		return quantityReceived;
	}

	public void setQuantityReceived(double quantityReceived) {
		this.quantityReceived = quantityReceived;
	}

	public double getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(double quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public double getGstTax() {
		return gstTax;
	}

	public void setGstTax(double gstTax) {
		this.gstTax = gstTax;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getCostPriceInclusiveTax() {
		return costPriceInclusiveTax;
	}

	public void setCostPriceInclusiveTax(double costPriceInclusiveTax) {
		this.costPriceInclusiveTax = costPriceInclusiveTax;
	}

	public double getMarginInPercentage() {
		return marginInPercentage;
	}

	public void setMarginInPercentage(double marginInPercentage) {
		this.marginInPercentage = marginInPercentage;
	}

	public double getMarginInRupees() {
		return marginInRupees;
	}

	public void setMarginInRupees(double marginInRupees) {
		this.marginInRupees = marginInRupees;
	}

	public double getDiscountInPercentage() {
		return discountInPercentage;
	}

	public void setDiscountInPercentage(double discountInPercentage) {
		this.discountInPercentage = discountInPercentage;
	}

	public double getDiscountInRupees() {
		return discountInRupees;
	}

	public void setDiscountInRupees(double discountInRupees) {
		this.discountInRupees = discountInRupees;
	}

	public double getLowinvnt() {
		return lowinvnt;
	}

	public void setLowinvnt(double lowinvnt) {
		this.lowinvnt = lowinvnt;
	}

	public double getMrpPerUnit() {
		return mrpPerUnit;
	}

	public void setMrpPerUnit(double mrpPerUnit) {
		this.mrpPerUnit = mrpPerUnit;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public double getTotalUnitPrice() {
		return totalUnitPrice;
	}

	public void setTotalUnitPrice(double totalUnitPrice) {
		this.totalUnitPrice = totalUnitPrice;
	}

	
}
