package com.beatus.billlive.sendsms.utils;

public class Constants {

	public static final String X_FRAME_OPTIONS = "X-Frame-Options";
	public static final String X_FRAME_OPTIONS_VALUE = "DENY";
	public static final String X_XSS_PROTECTION = "X-XSS-Protection";
	public static final String X_XSS_PROTECTION_VALUE = "1; mode=block";
	public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";
	public static final String STRICT_TRANSPORT_SECURITY_VALUE = "max-age=16070400; includeSubDomains";
    public static final String URL_PARAM_ACTION = "action";
    public static final String URL_PARAM_ERROR_CODE = "errorCode";
    
    public static final String WEB_PRODUCTS_REQUEST = "/product";
    public static final String WEB_DISTRIBUTOR_REQUEST = "/distributor";
    public static final String WEB_SMS_REQUEST = "/sms";
	public static final String WEB_PRODUCTS_ADD_PRODUCT = "/addProduct";
	public static final String WEB_DISTRIBUTOR_ADD_DISTRIBUTOR = "/addDistributor";
	public static final String WEB_PRODUCTS_GET_PRODUCTS = "/getProducts";
	public static final String WEB_DISTRIBUTOR_GET_DISTRIBUTORS = "/getDistributors";
    public static final String WEB_SMS_SEND_SMS_SCREEN = "/sendsmsScreen";
	public static final String PRODUCT = "product";
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_CATEGORY = "productCategory";
	public static final String PRODUCT_PRICE = "productPrice";
	public static final String DISTRIBUTOR = "distributor";
	public static final String DISTRIBUTOR_NAME = "distributorName";
	public static final String DISTRIBUTOR_PHONE = "distributorPhone";
	public static final String DISTRIBUTOR_LOCATION = "distributorLocation";
	public static final String DISTRIBUTOR_DISTRICT = "distributorDistrict";
	public static final String PRODUCTS = "Products";
	public static final String REDIRECT = "redirect:";
	public static final String WEB_LOCATION_REQUEST = "/location";
	public static final String WEB_LOCATION_ADD_LOCATION = "/addLocation";
	public static final String WEB_LOCATION_GET_LOCATIONS = "/getLocations";
	public static final String LOCATION = "location";
	public static final String LOCATION_NAME = "locationName";
	public static final String LOCATION_CITY = "locationCity";
	public static final String LOCATION_DISTRICT = "locationDistrict";
	public static final String LOCATION_STATE = "locationState";
	public static final String PRODUCT_LOCATION = "productLocation";
	public static final String WEB_PRODUCTS_ADD_PRODUCT_AND_LOCATION = "/addProductAndLocation";
	public static final String WEB_PRODUCTS_GET_PRODUCTS_AND_LOCATIONS = "/getProductsAndLocations";
	public static final String PRODUCT_LOCATION_NAME = "productandlocation";
	public static final String WEB_LOCATION_EDIT_LOCATION = "/editLocation";
	public static final String WEB_PRODUCTS_EDIT_PRODUCT = "/editProduct";
	public static final String WEB_PRODUCTS_EDIT_PRODUCT_AND_LOCATION = "/editProductsAndLocations";
	public static final String WEB_DISTRIBUTOR_EDIT_DISTRIBUTOR = "editDistributor";
	public static final String WEB_SMS_SEND_SMS_ADD_SCREEN_CONFIGURATION = "/addSmsConfiguration";
	public static final String WEB_SMS_SCREEN_GET_CONFIGURATION = "/getSmsConfiguration";
}
