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

	public String addProductAndLocation(ProductsAndLocations product) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		productRepository.addProductAndLocation(product);
		return Constants.REDIRECT + "/product/getProductsAndLocations";
	}

	public String addProduct(Product product) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		productRepository.addProduct(product);
		return Constants.REDIRECT + "/product/getProducts";
	}

	public List<ProductsAndLocations> getProductsAndLocations() throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		List<ProductsAndLocations> products = productRepository.getProductsAndLocations();
		return products;
	}

	public List<Product> getProducts() throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		List<Product> products = productRepository.getProducts();
		return products;
	}

	public Product getProductById(int productId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		Product product = productRepository.getProductById(productId);
		return product;
	}

	public ProductsAndLocations getProductAndLocationById(int productId, int locationId)
			throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		ProductsAndLocations product = productRepository.getProductAndLocationById(productId, locationId);
		return product;
	}

	public List<Location> getLocations() throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductAndLocation");
		return locationService.getLocations();
	}

	public String editProductAndLocation(ProductsAndLocations product) throws SQLException {
		LOGGER.info("In addProductAndLocation");
		productRepository.editProductAndLocation(product);
		return Constants.REDIRECT + "/product/getProductsAndLocations";
	}

	public String editProduct(Product product) throws SQLException {
		LOGGER.info("In addProductAndLocation");
		productRepository.editProduct(product);
		return Constants.REDIRECT + "/product/getProducts";
	}

}