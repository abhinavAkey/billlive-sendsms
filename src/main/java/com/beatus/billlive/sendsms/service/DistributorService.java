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

import com.beatus.billlive.sendsms.model.Distributor;
import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.utils.Constants;

@Service
@Component("distributorService")
public class DistributorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorService.class);

	@Resource(name = "locationService")
    private LocationService locationService;
	
    public String addDistributor(Distributor distributor) {
    	
    	LOGGER.info("In addDistributor");
    	
    	ClassLoader classLoader = getClass().getClassLoader();
    	File xmlFile = new File(classLoader.getResource("xml-datafiles/distributors.xml").getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
        	dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile);
			LOGGER.info("doc " + doc);
			// create the root element
			Element docEle = doc.getDocumentElement();

			Element distributorEle = null;
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node node = (Node) xpath.evaluate("//*[@id='"+distributor.getDistributorName()+"-"+distributor.getDistributorPhone()+"']", doc, XPathConstants.NODE);
			if (node == null) {
				distributorEle = doc.createElement(Constants.DISTRIBUTOR);
				
				Attr attr = doc.createAttribute("id");
				attr.setValue(distributor.getDistributorName()+"-"+distributor.getDistributorPhone());
				distributorEle.setAttributeNode(attr);
				
				// create data elements and place them under root
	            Element e = doc.createElement(Constants.DISTRIBUTOR_NAME);
	            e.appendChild(doc.createTextNode(distributor.getDistributorName()));
	            distributorEle.appendChild(e);

	            e = doc.createElement(Constants.DISTRIBUTOR_PHONE);
	            e.appendChild(doc.createTextNode(distributor.getDistributorPhone()));
	            distributorEle.appendChild(e);

	            e = doc.createElement(Constants.DISTRIBUTOR_LOCATION);
	            e.appendChild(doc.createTextNode(distributor.getDistributorLocation()));
	            distributorEle.appendChild(e);
	            
	            docEle.appendChild(distributorEle);
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
            
        }catch(Exception e){
        	return e.getMessage();
        }
		return "distributor/request-get";
    }
    
    public List<Distributor> getDistributors() {
		List<Distributor> distributors = new ArrayList<Distributor>();
		Document dom;
		// Make an instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File xmlFile = new File(classLoader.getResource("xml-datafiles/distributors.xml").getFile());
			// use the factory to take an instance of the document builder
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
						Distributor distributor = new Distributor();
						if (el.getNodeName().contains(Constants.DISTRIBUTOR)) {
							String name = el.getElementsByTagName(Constants.DISTRIBUTOR_NAME).item(0).getTextContent();
							distributor.setDistributorName(name);
							String phone = el.getElementsByTagName(Constants.DISTRIBUTOR_PHONE).item(0).getTextContent();
							distributor.setDistributorPhone(phone);
							String location = el.getElementsByTagName(Constants.DISTRIBUTOR_LOCATION).item(0).getTextContent();
							distributor.setDistributorLocation(location);
						}
						distributors.add(distributor);
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
		return distributors;
	}

	public List<Location> getLocations() {
		return locationService.getLocations();
	}

	public void editDistributor(Distributor distributor) {

		
	}

}