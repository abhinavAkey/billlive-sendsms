package com.beatus.billlive.sendsms.repository;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.billlive.sendsms.model.Product;
import com.beatus.billlive.sendsms.model.ProductsAndLocations;

@Service
@Component("productRepository")
public class ProductRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepository.class);

	@Autowired
    @Qualifier(value = "connection")
	private Connection conn;

	public String addProductAndLocation(ProductsAndLocations product) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addProduct " + product.getProductName());
		String sql = "INSERT INTO product_location (product_id, location_id, price) VALUES (?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, product.getProductId());
		statement.setInt(2, product.getProductLocationId());
		statement.setString(3, product.getProductPrice());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new addProductAndLocations was inserted successfully!");
		}
		
		return "product/request-get";
	}
	
	public String addProduct(Product product) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addProduct " + product.getProductName());
		String sql = "INSERT INTO product (product_name, product_category , product_image) VALUES (?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, product.getProductName());
		statement.setString(2, product.getProductCategory());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(product.getProductImage());
		statement.setBlob(3, blob);
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was inserted successfully!");
		}
        return "product/request-get";
	}

	public List<ProductsAndLocations> getProductsAndLocations() throws ClassNotFoundException, SQLException {
		List<ProductsAndLocations> productsAndLocations = new ArrayList<ProductsAndLocations>();
		String sql = "SELECT proAndLoc.product_location_id AS productLocationId, pro.product_id AS productId, pro.product_name AS productName, "
				+ "pro.product_category AS productCategory, loc.location_id AS locationId, "
				+ "loc.location_name AS locationName, proAndLoc.price as productPrice "
				+ "FROM product pro, location loc, product_location proAndLoc "
				+ "WHERE proAndLoc.location_id = loc.location_id AND proAndLoc.product_id = pro.product_id";
 
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
			ProductsAndLocations productsAndLocation = new ProductsAndLocations();
			productsAndLocation.setProductCategory(result.getString("productCategory"));
			productsAndLocation.setProductLocationId(result.getInt("productLocationId"));
			productsAndLocation.setProductName(result.getString("productName"));
			productsAndLocation.setProductLocationName(result.getString("locationName"));
			productsAndLocation.setProductPrice(result.getString("productPrice"));
			productsAndLocation.setLocationId(result.getInt("locationId"));
			productsAndLocation.setProductId(result.getInt("productId"));
			
			productsAndLocations.add(productsAndLocation);
		}
		return productsAndLocations;
	}
	
	public List<Product> getProducts() throws ClassNotFoundException, SQLException {
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM product";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
			Product product = new Product();
			product.setProductName(result.getString("product_name"));
			product.setProductCategory(result.getString("product_category"));
			Blob blob = result.getBlob("product_image");
			int blobLength = (int) blob.length(); 
			product.setProductImage(blob.getBytes(0, blobLength));
			products.add(product);
		}
		return products;
	}
	
	public Product getProductById(int productId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM product where product_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, productId);
		ResultSet result = statement.executeQuery();
		Product product = new Product();
		while (result.next()){
			
			product.setProductName(result.getString("product_name"));
			product.setProductCategory(result.getString("product_category"));
			Blob blob = result.getBlob("product_image");
			int blobLength = (int) blob.length(); 
			product.setProductImage(blob.getBytes(0, blobLength));
			
		}
		return product;
	}
	public ProductsAndLocations getProductAndLocationById(int productId, int locationId) throws ClassNotFoundException, SQLException {
		String productSql = "SELECT proAndLoc.product_location_id AS productLocationId, pro.product_id AS productId, pro.product_name AS productName, "
				+ "pro.product_category AS productCategory, loc.location_id AS locationId, "
				+ "loc.location_name AS locationName, proAndLoc.price as productPrice "
				+ "FROM product pro, location loc, product_location proAndLoc "
				+ "WHERE proAndLoc.location_id = loc.location_id AND proAndLoc.product_id = pro.product_id AND proAndLoc.location_id = ? AND proAndLoc.product_id = ?";

		PreparedStatement statement = conn.prepareStatement(productSql);
		statement.setInt(1, locationId);
		statement.setInt(2, productId);
		ResultSet result = statement.executeQuery();
		ProductsAndLocations productsAndLocation = new ProductsAndLocations();
		while (result.next()){
			productsAndLocation.setProductCategory(result.getString("productCategory"));
			productsAndLocation.setProductLocationId(result.getInt("productLocationId"));
			productsAndLocation.setProductName(result.getString("productName"));
			productsAndLocation.setProductLocationName(result.getString("locationName"));
			productsAndLocation.setProductPrice(result.getString("productPrice"));
			productsAndLocation.setLocationId(result.getInt("locationId"));
			productsAndLocation.setProductId(result.getInt("productId"));
			
		}
		return productsAndLocation;
	}
	
	public void editProductAndLocation(ProductsAndLocations product) throws SQLException {
		LOGGER.info("In addProduct " + product.getProductName());
		String sql = "UPDATE product_location SET product_id = ?, location_id ?, price ? WHERE product_location_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, product.getProductId());
		statement.setInt(2, product.getLocationId());
		statement.setString(3, product.getProductPrice());
		statement.setInt(4, product.getProductLocationId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new addProductAndLocations was inserted successfully!");
		}
	}

	public void editProduct(Product product) throws SQLException {
		LOGGER.info("In addProduct " + product.getProductName());
		String sql = "UPDATE product SET product_name = ?,  product_category = ?, product_image = ?) WHERE product_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, product.getProductName());
		statement.setString(2, product.getProductCategory());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(product.getProductImage());
		statement.setBlob(3, blob);
		statement.setInt(4, product.getProductId());
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was inserted successfully!");
		}
	}

}