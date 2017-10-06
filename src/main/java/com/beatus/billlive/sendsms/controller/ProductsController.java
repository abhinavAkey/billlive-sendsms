package com.beatus.billlive.sendsms.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.Product;
import com.beatus.billlive.sendsms.model.ProductsAndLocations;
import com.beatus.billlive.sendsms.model.ProductsAndLocationsResponse;
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
    public String addProductPost(HttpServletRequest request, Product product, ModelMap model) throws ClassNotFoundException, SQLException {
    	LOGGER.info("In addProductPost");
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	
    	String resp = productService.addProduct(product, companyId, uid);
		LOGGER.info("product======="+product.toString());
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_EDIT_PRODUCT,
            method = RequestMethod.POST)
    public String editProductPost(HttpServletRequest request, Product product, ModelMap model) throws SQLException {
    	LOGGER.info("In addProductPost");
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	
		String resp = productService.editProduct(product, companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_ADD_PRODUCT_AND_LOCATION,
            method = RequestMethod.GET)
    public String addProductAndLocationsGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	
    	List<Product> products = productService.getProducts(companyId);
    	model.addAttribute("products", products);
    	List<Location> locations = productService.getLocations(companyId);
    	model.addAttribute("locations", locations);
        return "product/request-product-location-add";
    } 
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_ADD_PRODUCT_AND_LOCATION,
            method = RequestMethod.POST)
    public String addProductAndLocationPost(HttpServletRequest request, ProductsAndLocations product, ModelMap model) throws ClassNotFoundException, SQLException {
    	LOGGER.info("In addProductPost");
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	
		String resp = productService.addProductAndLocation(product, companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_EDIT_PRODUCT_AND_LOCATION,
            method = RequestMethod.POST)
    public String editProductAndLocationPost(HttpServletRequest request, ProductsAndLocations product, ModelMap model) throws SQLException {
    	LOGGER.info("In addProductPost");
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	
		String resp = productService.editProductAndLocation(product, companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_GET_PRODUCTS,
            method = RequestMethod.GET)
    public String getProductsGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	
    	List<Product> products = productService.getProducts(companyId);
    	LOGGER.info("After the get call and the products are "  + products != null? products.size() > 0 ? products.get(0).getProductName() : "No Product data" : "No Product data");
    	ProductsResponse resp = new ProductsResponse();
        resp.setProducts(products);
    	model.addAttribute("productsResp", resp); 
    	model.addAttribute("products", products); 
    	return "product/request-product-get";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_GET_PRODUCTS_AND_LOCATIONS,
            method = RequestMethod.GET)
    public String getProductsAndLocationsGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	
    	List<ProductsAndLocations> products = productService.getProductsAndLocations(companyId);
    	LOGGER.info("After the get call and the products are "  + products != null? products.size() > 0 ? products.get(0).getProductName() : "No Product data" : "No Product data");
    	ProductsAndLocationsResponse resp = new ProductsAndLocationsResponse();
        resp.setProductsAndLocations(products);
    	model.addAttribute("productsResp", resp); 
    	model.addAttribute("products", products); 
    	return "bill/request-products-locations-get";
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_DELETE_PRODUCTS,
            method = RequestMethod.POST)
    public String deleteProductPost(HttpServletRequest request, Product product, ModelMap model) throws SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = productService.deleteProduct(product.getProductId(), companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_DELETE_PRODUCTS_AND_LOCATIONS,
            method = RequestMethod.POST)
    public String deleteProductsAndLocationsPost(HttpServletRequest request, ProductsAndLocations product, ModelMap model) throws SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = productService.deleteProductAndLocations(product.getProductLocationId(), companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_PRODUCTS_GET_PRODUCT_BY_ID,
            method = RequestMethod.GET)
    public String getProductByProductId(HttpServletRequest request, ModelMap model, @RequestBody int productId) throws SQLException, ClassNotFoundException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	Product product = productService.getProductById(productId , companyId );
    	String resp = null;
    	return resp;
    }
    

}
