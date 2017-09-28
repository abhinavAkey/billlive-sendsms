package com.beatus.billlive.sendsms.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.Product;
import com.beatus.billlive.sendsms.model.ProductsAndLocations;
import com.beatus.billlive.sendsms.repository.ProductRepository;
import com.beatus.billlive.sendsms.utils.Constants;

@Service
@Component("productService")
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Resource(name = "locationService")
	private LocationService locationService;
	
	@Resource(name = "productRepository")
	private ProductRepository productRepository;

	public String addProductAndLocation(ProductsAndLocations product, String companyId, String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		product.setCompanyId(companyId);
		product.setUid(uid);
		productRepository.addProductAndLocation(product);
		return Constants.REDIRECT + "/product/getProductsAndLocations";
	}

	public String addProduct(Product product, String companyId, String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		product.setCompanyId(companyId);
		product.setUid(uid);
		productRepository.addProduct(product);
		return Constants.REDIRECT + "/product/getProducts";
	}

	public List<ProductsAndLocations> getProductsAndLocations(String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		List<ProductsAndLocations> products = productRepository.getProductsAndLocations(companyId);
		return products;
	}

	public List<Product> getProducts(String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		List<Product> products = productRepository.getProducts(companyId);
		return products;
	}

	public Product getProductById(int productId, String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		Product product = productRepository.getProductById(productId, companyId);
		return product;
	}

	public ProductsAndLocations getProductAndLocationById(int productId, int locationId, String companyId)
			throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		ProductsAndLocations product = productRepository.getProductAndLocationById(productId, locationId, companyId);
		return product;
	}

	public List<Location> getLocations(String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		return locationService.getLocations(companyId);
	}

	public String editProductAndLocation(ProductsAndLocations product, String companyId, String uid) throws SQLException {
		LOGGER.info("In addProductAndLocation");
		product.setCompanyId(companyId);
		product.setUid(uid);
		productRepository.editProductAndLocation(product);
		return Constants.REDIRECT + "/product/getProductsAndLocations";
	}

	public String editProduct(Product product, String companyId, String uid) throws SQLException {
		LOGGER.info("In addProductAndLocation");
		product.setCompanyId(companyId);
		product.setUid(uid);
		productRepository.editProduct(product);
		return Constants.REDIRECT + "/product/getProducts";
	}
	
	public String deleteProduct(int productId, String companyId, String uid) throws SQLException {
		LOGGER.info("In deleteProduct");

		boolean isDeleted = productRepository.deleteProduct(productId, companyId);
		if(isDeleted){
			return Constants.REDIRECT + "/product/getProducts";
		}else {
			//TO-DO add a message that the row is not deleted
			return Constants.REDIRECT + "/product/getProducts";
		}
	}
	
	public String deleteProductAndLocations(int productLocationId, String companyId, String uid) throws SQLException {
		LOGGER.info("In deleteProductAndLocations");

		boolean isDeleted = productRepository.deleteProductsAndLocations(productLocationId, companyId);
		if(isDeleted){
			return Constants.REDIRECT + "/product/getProductsAndLocations";
		}else {
			//TO-DO add a message that the row is not deleted
			return Constants.REDIRECT + "/product/getProductsAndLocations";
		}
	}

}