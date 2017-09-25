package com.beatus.billlive.sendsms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.billlive.sendsms.model.Location;

@Service
@Component("locationService")
public class LocationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);
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
	public String addLocation(Location location) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addLocation");

		/*ClassLoader classLoader = getClass().getClassLoader();
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
			Element locationEle = null;
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node node = (Node) xpath.evaluate("//*[@id='"+location.getLocationName()+"']", doc, XPathConstants.NODE);
			if (node == null) {
				locationEle = doc.createElement(Constants.LOCATION);

				Attr attr = doc.createAttribute("id");
				attr.setValue(location.getLocationName());
				locationEle.setAttributeNode(attr);
				
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
		
		String sql = "INSERT INTO location (locationName, locationCity, locationDistrict, locationState) VALUES (?, ?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, location.getLocationName());
		statement.setString(2, location.getLocationCity());
		statement.setString(3, location.getLocationDistrict());
		statement.setString(4, location.getLocationState());
		
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was inserted successfully!");
		}

		return "location/request-get";
	}

	public List<Location> getLocations() throws ClassNotFoundException, SQLException {
		List<Location> locations = new ArrayList<Location>();
		/*Document dom;
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
							String district = el.getElementsByTagName(Constants.LOCATION_DISTRICT).item(0)
									.getTextContent();
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
		}*/
		
		String sql = "SELECT * FROM location";
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
			Location location = new Location();
			location.setLocationId(result.getInt("locationId"));
			location.setLocationName(result.getString("locationName"));
			location.setLocationState(result.getString("locationCity"));
			location.setLocationCity(result.getString("locationDistrict"));
			location.setLocationDistrict(result.getString("locationState"));
			locations.add(location);
		}
		return locations;
	}
	public Location getLocationById(int locationId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM location where locationId = ?";
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, locationId);
		ResultSet result = statement.executeQuery(sql);
		Location location = new Location();
		while (result.next()){
			location.setLocationId(result.getInt("locationId"));
			location.setLocationName(result.getString("locationName"));
			location.setLocationState(result.getString("locationCity"));
			location.setLocationCity(result.getString("locationDistrict"));
			location.setLocationDistrict(result.getString("locationState"));
		}
		return location;
	}
	public void editLocation(Location location) {
		
	}

}