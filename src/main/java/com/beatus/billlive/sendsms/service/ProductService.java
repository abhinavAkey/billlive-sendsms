package com.beatus.billlive.sendsms.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.Product;
import com.beatus.billlive.sendsms.utils.Constants;

@Service
@Component("productService")
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Resource(name = "locationService")
    private LocationService locationService;
	
	public String addProductAndLocation(Product product) {

		LOGGER.info("In addProduct " + product.getProductName());
		ClassLoader classLoader = getClass().getClassLoader();
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
		}
		return "product/request-get";
	}
	
	public String addProduct(Product product) {

		LOGGER.info("In addProduct " + product.getProductName());
		ClassLoader classLoader = getClass().getClassLoader();
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
		}
		return "product/request-get";
	}

	public List<Product> getProductsAndLocations() {
		List<Product> products = new ArrayList<Product>();
		Document dom;
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
		}

		return products;
	}
	
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		Document dom;
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
		}

		return products;
	}

	public List<Location> getLocations() {
		return locationService.getLocations();
	}

	public void editProductAndLocation(Product product) {
		
	}

	public void editProduct(Product product) {
		
	}

}