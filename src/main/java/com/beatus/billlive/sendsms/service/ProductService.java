package com.beatus.billlive.sendsms.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.beatus.billlive.sendsms.model.Product;
import com.beatus.billlive.sendsms.utils.Constants;

@Service
@Component("productService")
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

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

			Element productEle = doc.createElement(Constants.PRODUCT);

			// create data elements and place them under root
			Element name = doc.createElement(Constants.PRODUCT_NAME);
			name.appendChild(doc.createTextNode(product.getProductName()));
			productEle.appendChild(name);

			Element price = doc.createElement(Constants.PRODUCT_PRICE);
			price.appendChild(doc.createTextNode(product.getProductPrice()));
			productEle.appendChild(price);

			Element cat = doc.createElement(Constants.PRODUCT_CATEGORY);
			cat.appendChild(doc.createTextNode(product.getProductCategory()));
			productEle.appendChild(cat);

			docEle.appendChild(productEle);
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
							String price = el.getElementsByTagName(Constants.PRODUCT_PRICE).item(0).getTextContent();
							product.setProductPrice(price);
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

	private static void addElement(Document doc, Product product) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;

		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			Element salaryElement = doc.createElement("salary");
			salaryElement.appendChild(doc.createTextNode("10000"));
			emp.appendChild(salaryElement);
		}
	}

	private static void deleteElement(Document doc) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;
		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			Node genderNode = emp.getElementsByTagName("gender").item(0);
			emp.removeChild(genderNode);
		}

	}

	private static void updateElementValue(Document doc) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;
		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
			name.setNodeValue(name.getNodeValue().toUpperCase());
		}
	}

	private static void updateAttributeValue(Document doc) {
		NodeList employees = doc.getElementsByTagName("Employee");
		Element emp = null;
		// loop for each employee
		for (int i = 0; i < employees.getLength(); i++) {
			emp = (Element) employees.item(i);
			String gender = emp.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
			if (gender.equalsIgnoreCase("male")) {
				// prefix id attribute with M
				emp.setAttribute("id", "M" + emp.getAttribute("id"));
			} else {
				// prefix id attribute with F
				emp.setAttribute("id", "F" + emp.getAttribute("id"));
			}
		}
	}

}