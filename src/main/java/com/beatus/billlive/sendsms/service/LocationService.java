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

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.utils.Constants;

@Service
@Component("locationService")
public class LocationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);


    public String addLocation(Location location) {
    	
    	LOGGER.info("In addLocation");
    	
    	ClassLoader classLoader = getClass().getClassLoader();
    	File xmlFile = new File(classLoader.getResource("xml-datafiles/locations.xml").getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
        	dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(xmlFile);
			LOGGER.info("doc " + doc);
			// create the root element
			Element docEle = doc.getDocumentElement();

            
            // create the root element
            Element locationEle = doc.createElement(Constants.LOCATION);

            // create data elements and place them under root
            Element e = doc.createElement(Constants.LOCATION_NAME);
            e.appendChild(doc.createTextNode(location.getLocationName()));
            locationEle.appendChild(e);

            e = doc.createElement(Constants.LOCATION_CITY);
            e.appendChild(doc.createTextNode(location.getLocationCity()));
            locationEle.appendChild(e);

            e = doc.createElement(Constants.LOCATION_DISTRICT);
            e.appendChild(doc.createTextNode(location.getLocationDistrict()));
            locationEle.appendChild(e);
            
            e = doc.createElement(Constants.LOCATION_STATE);
            e.appendChild(doc.createTextNode(location.getLocationState()));
            locationEle.appendChild(e);
            
            docEle.appendChild(locationEle);
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
		return "location/request-get";
    }
    
    public List<Location> getLocations() {
		List<Location> locations = new ArrayList<Location>();
		Document dom;
		// Make an instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File xmlFile = new File(classLoader.getResource("xml-datafiles/locations.xml").getFile());
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
						Location location = new Location();
						if (el.getNodeName().contains(Constants.LOCATION)) {
							String name = el.getElementsByTagName(Constants.LOCATION_NAME).item(0).getTextContent();
							location.setLocationName(name);
							String city = el.getElementsByTagName(Constants.LOCATION_CITY).item(0).getTextContent();
							location.setLocationCity(city);
							String district = el.getElementsByTagName(Constants.LOCATION_DISTRICT).item(0).getTextContent();
							location.setLocationDistrict(district);
							String state = el.getElementsByTagName(Constants.LOCATION_STATE).item(0).getTextContent();
							location.setLocationState(state);
							
						}
						locations.add(location);
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
		return locations;
	}

    private static void addElement(Document doc, Location location) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Element salaryElement = doc.createElement("salary");
            salaryElement.appendChild(doc.createTextNode("10000"));
            emp.appendChild(salaryElement);
        }
    }

    private static void deleteElement(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node genderNode = emp.getElementsByTagName("gender").item(0);
            emp.removeChild(genderNode);
        }
        
    }

    private static void updateElementValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase());
        }
    }

    private static void updateAttributeValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            String gender = emp.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
            if(gender.equalsIgnoreCase("male")){
                //prefix id attribute with M
                emp.setAttribute("id", "M"+emp.getAttribute("id"));
            }else{
                //prefix id attribute with F
                emp.setAttribute("id", "F"+emp.getAttribute("id"));
            }
        }
    }

}