package com.beatus.billlive.sendsms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.Product;
import com.beatus.billlive.sendsms.model.ProductsResponse;
import com.beatus.billlive.sendsms.service.ProductService;
import com.beatus.billlive.sendsms.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_PRODUCTS_REQUEST)
public class ProductsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

	@Resource(name = "productService")
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String productHome(HttpServletRequest request, ModelMap model) {
        return "product/home";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_ADD_PRODUCT,
            method = RequestMethod.GET)
    public String addProductGet(HttpServletRequest request, ModelMap model) {
    	return "product/request-product-add";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_ADD_PRODUCT,
            method = RequestMethod.POST)
    public String addProductPost(HttpServletRequest request, Product product, ModelMap model) {
    	LOGGER.info("In addProductPost");
		productService.addProduct(product);
    	return Constants.REDIRECT + "/product/getProducts";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_EDIT_PRODUCT,
            method = RequestMethod.POST)
    public String editProductPost(HttpServletRequest request, Product product, ModelMap model) {
    	LOGGER.info("In addProductPost");
		productService.editProduct(product);
    	return Constants.REDIRECT + "/product/getProducts";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_ADD_PRODUCT_AND_LOCATION,
            method = RequestMethod.GET)
    public String addProductAndLocationsGet(HttpServletRequest request, ModelMap model) {
    	List<Product> products = productService.getProducts();
    	model.addAttribute("products", products);
    	List<Location> locations = productService.getLocations();
    	model.addAttribute("locations", locations);
        return "product/request-product-location-add";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_ADD_PRODUCT_AND_LOCATION,
            method = RequestMethod.POST)
    public String addProductAndLocationPost(HttpServletRequest request, Product product, ModelMap model) {
    	LOGGER.info("In addProductPost");
		productService.addProductAndLocation(product);
    	return Constants.REDIRECT + "/product/getProductsAndLocations";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_EDIT_PRODUCT_AND_LOCATION,
            method = RequestMethod.POST)
    public String editProductAndLocationPost(HttpServletRequest request, Product product, ModelMap model) {
    	LOGGER.info("In addProductPost");
		productService.editProductAndLocation(product);
    	return Constants.REDIRECT + "/product/getProductsAndLocations";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_GET_PRODUCTS,
            method = RequestMethod.GET)
    public String getProductsGet(HttpServletRequest request, ModelMap model) {
    	List<Product> products = productService.getProducts();
    	LOGGER.info("After the get call and the products are "  + products != null? products.size() > 0 ? products.get(0).getProductName() : "No Product data" : "No Product data");
    	ProductsResponse resp = new ProductsResponse();
        resp.setProducts(products);
    	model.addAttribute("productsResp", resp); 
    	model.addAttribute("products", products); 
    	return "product/request-product-get";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_GET_PRODUCTS_AND_LOCATIONS,
            method = RequestMethod.GET)
    public String getProductsAndLocationsGet(HttpServletRequest request, ModelMap model) {
    	List<Product> products = productService.getProductsAndLocations();
    	LOGGER.info("After the get call and the products are "  + products != null? products.size() > 0 ? products.get(0).getProductName() : "No Product data" : "No Product data");
    	ProductsResponse resp = new ProductsResponse();
        resp.setProducts(products);
    	model.addAttribute("productsResp", resp); 
    	model.addAttribute("products", products); 
    	return "product/request-products-locations-get";
    }
}
