package com.beatus.billlive.sendsms.service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.Product;

@Service
@Component("productService")
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Resource(name = "locationService")
    private LocationService locationService;
	public Connection getConnection() throws ClassNotFoundException{
		 Class.forName("com.mysql.jdbc.Driver");

	    	String dbURL = "jdbc:mysql://localhost:3306/billlive_sendsms";
	    	String username = "root";
	    	String password = "root";
	    	Connection conn = null;
	    	try {
	    		conn = DriverManager.getConnection(dbURL, username, password);
	    	if (conn != null) {
	    	System.out.println("Connected");
	    	}
	    	} catch (SQLException ex) {
	    	ex.printStackTrace();
	    	}
	    	LOGGER.info("In addDistributor");
	    	return conn;
		 
	 }
	public String addProductAndLocation(Product product) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addProduct " + product.getProductName());
		String sql = "INSERT INTO productandlocation (productid, locationid, price) VALUES (?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, product.getProductId());
		statement.setInt(2, product.getProductLocationId());
		statement.setString(3, product.getProductPrice());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was inserted successfully!");
		}
        
		/*ClassLoader classLoader = getClass().getClassLoader();
		File xmlFile = new File(classLoader.getResource("xml-datafiles/productslocations.xml").getFile());
		LOGGER.info("xmlFile " + xmlFile);
		LOGGER.info("xmlFile size" + xmlFile.getPath());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile);
			LOGGER.info("doc " + doc);
			// create the root element
			Element docEle = doc.getDocumentElement();

			Element productEle = null;
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node node = (Node) xpath.evaluate("//*[@id='"+product.getProductName()+"-"+product.getProductCategory()+"-"+product.getProductLocation()+"']", doc, XPathConstants.NODE);
			if (node == null) {
		    	productEle = doc.createElement(Constants.PRODUCT_LOCATION);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(product.getProductName()+"-"+product.getProductCategory()+"-"+product.getProductLocation());
				productEle.setAttributeNode(attr);
				
				// create data elements and place them under root
				Element nameLocation = doc.createElement(Constants.PRODUCT_LOCATION_NAME);
				nameLocation.appendChild(doc.createTextNode(product.getProductLocation() + "-" +product.getProductName()));
				productEle.appendChild(nameLocation);
				
				Element name = doc.createElement(Constants.PRODUCT_NAME);
				name.appendChild(doc.createTextNode(product.getProductName()));
				productEle.appendChild(name);

				Element price = doc.createElement(Constants.PRODUCT_PRICE);
				price.appendChild(doc.createTextNode(product.getProductPrice()));
				productEle.appendChild(price);

				Element loc = doc.createElement(Constants.PRODUCT_LOCATION);
				loc.appendChild(doc.createTextNode(product.getProductLocation()));
				productEle.appendChild(loc);

				docEle.appendChild(productEle);
		    }

			LOGGER.info("doc " + doc);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			LOGGER.info("source " + source);
			StreamResult result = new StreamResult(xmlFile);
			LOGGER.info("result " + result);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			LOGGER.info("XML file updated successfully");

		} catch (Exception e) {
			return e.getMessage();
		}*/
	
		return "product/request-get";
	}
	
	public String addProduct(Product product) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addProduct " + product.getProductName());
		/*ClassLoader classLoader = getClass().getClassLoader();
		File xmlFile = new File(classLoader.getResource("xml-datafiles/products.xml").getFile());
		LOGGER.info("xmlFile " + xmlFile);
		LOGGER.info("xmlFile size" + xmlFile.getPath());

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile);
			LOGGER.info("doc " + doc);
			// create the root element
			Element docEle = doc.getDocumentElement();

			Element productEle = null;
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node node = (Node) xpath.evaluate("//*[@id='"+product.getProductName()+"-"+product.getProductCategory()+"']", doc, XPathConstants.NODE);
			if (node == null) {
		    	productEle = doc.createElement(Constants.PRODUCT);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(product.getProductName()+"-"+product.getProductCategory());
				productEle.setAttributeNode(attr);
				
				// create data elements and place them under root
				Element name = doc.createElement(Constants.PRODUCT_NAME);
				name.appendChild(doc.createTextNode(product.getProductName()));
				productEle.appendChild(name);
				

				Element cat = doc.createElement(Constants.PRODUCT_CATEGORY);
				cat.appendChild(doc.createTextNode(product.getProductCategory()));
				productEle.appendChild(cat);
				
				docEle.appendChild(productEle);
		    }
			
			LOGGER.info("doc " + doc);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			LOGGER.info("source " + source);
			StreamResult result = new StreamResult(xmlFile);
			LOGGER.info("result " + result);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			LOGGER.info("XML file updated successfully");

		} catch (Exception e) {
			return e.getMessage();
		}*/
		String sql = "INSERT INTO product (productName, productCategory , productImage) VALUES (?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, product.getProductName());
		statement.setString(2, product.getProductCategory());
		Blob blob=null;
		blob.setBytes(1, product.getProductImage() );
		statement.setBlob(3, blob);
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was inserted successfully!");
		}
        return "product/request-get";
	}

	public List<Product> getProductsAndLocations() throws ClassNotFoundException, SQLException {
		List<Product> products = new ArrayList<Product>();
		/*Document dom;
		// Make an instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File xmlFile = new File(classLoader.getResource("xml-datafiles/productslocations.xml").getFile());
			// use the factory to take an instance of the document builder
			LOGGER.info("xmlFile " + xmlFile);
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(xmlFile);

			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getChildNodes();
			if (nl != null) {
				int length = nl.getLength();
				for (int i = 0; i < length; i++) {
					if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) nl.item(i);
						Product product = new Product();
						if (el.getNodeName().contains(Constants.PRODUCT)) {
							String name = el.getElementsByTagName(Constants.PRODUCT_NAME).item(0).getTextContent();
							product.setProductName(name);
							String price = el.getElementsByTagName(Constants.PRODUCT_PRICE).item(0).getTextContent();
							product.setProductPrice(price);
							String location = el.getElementsByTagName(Constants.PRODUCT_LOCATION).item(0)
									.getTextContent();
							product.setProductLocation(location);
						}
						products.add(product);
					}
				}
			}
		} catch (ParserConfigurationException pce) {
			LOGGER.info(pce.getMessage());
		} catch (SAXException se) {
			LOGGER.info(se.getMessage());
		} catch (IOException ioe) {
			LOGGER.info(ioe.getMessage());
		}*/
		String sql = "SELECT * FROM productandlocation";
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
			Product product = new Product();
			//product.setProductId(result.getInt("productId"));
			//product.setProductLocationId(result.getInt("locationId"));
			//product.setProductPrice(result.getDouble("price"));
			product = getProductAndLocationById(result.getInt("productId"),result.getInt("locationId") );
			products.add(product);
		}
		return products;
	}
	
	public List<Product> getProducts() throws ClassNotFoundException, SQLException {
		List<Product> products = new ArrayList<Product>();
		/*Document dom;
		// Make an instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File xmlFile = new File(classLoader.getResource("xml-datafiles/products.xml").getFile());
			// use the factory to take an instance of the document builder
			LOGGER.info("xmlFile " + xmlFile);
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(xmlFile);

			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getChildNodes();
			if (nl != null) {
				int length = nl.getLength();
				for (int i = 0; i < length; i++) {
					if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) nl.item(i);
						Product product = new Product();
						if (el.getNodeName().contains(Constants.PRODUCT)) {
							String name = el.getElementsByTagName(Constants.PRODUCT_NAME).item(0).getTextContent();
							product.setProductName(name);
							String category = el.getElementsByTagName(Constants.PRODUCT_CATEGORY).item(0)
									.getTextContent();
							product.setProductCategory(category);
						}
						products.add(product);
					}
				}
			}
		} catch (ParserConfigurationException pce) {
			LOGGER.info(pce.getMessage());
		} catch (SAXException se) {
			LOGGER.info(se.getMessage());
		} catch (IOException ioe) {
			LOGGER.info(ioe.getMessage());
		}*/
		String sql = "SELECT * FROM product";
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
			Product product = new Product();
			product.setProductName(result.getString("productName"));
			product.setProductCategory(result.getString("productCategory"));
			Blob blob = result.getBlob("productImage");
			int blobLength = (int) blob.length(); 
			product.setProductImage(blob.getBytes(0, blobLength));
			products.add(product);
		}
		return products;
	}
	
	public Product getProductById(int productId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM product where productid = ?";
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, productId);
		ResultSet result = statement.executeQuery();
		Product product = new Product();
		while (result.next()){
			
			product.setProductName(result.getString("productName"));
			product.setProductCategory(result.getString("productCategory"));
			Blob blob = result.getBlob("productImage");
			int blobLength = (int) blob.length(); 
			product.setProductImage(blob.getBytes(0, blobLength));
			
		}
		return product;
	}
	public Product getProductAndLocationById(int productId, int locationId) throws ClassNotFoundException, SQLException {
		String productSql = "SELECT product.productName,product.productCategory,product.productImage,location.locationName,productandlocation.productPrice   FROM productandlocation,product,location where productandlocation.productId = product.productId and productandlocation.locationId = location.locationId  and productandlocation.productId = ? and productandlocation.locationId = ?;";
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement(productSql);
		statement.setInt(1, productId);
		statement.setInt(1, locationId);
		ResultSet result = statement.executeQuery();
		Product product = new Product();
		while (result.next()){
			product.setProductName(result.getString("productName"));
			product.setProductCategory(result.getString("productCategory"));
			Blob blob = result.getBlob("productImage");
			int blobLength = (int) blob.length(); 
			product.setProductImage(blob.getBytes(0, blobLength));
			product.setProductLocationId(result.getInt("locationId"));
			product.setProductLocation(result.getString("locationName"));
			product.setProductPrice(result.getString("productPrice"));
		}
		return product;
	}

	public List<Location> getLocations() throws ClassNotFoundException, SQLException {
		return locationService.getLocations();
	}

	public void editProductAndLocation(Product product) {
		
	}

	public void editProduct(Product product) {
		
	}

}