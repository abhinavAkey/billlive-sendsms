package com.beatus.billlive.sendsms.service;

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

import com.beatus.billlive.sendsms.model.Distributor;
import com.beatus.billlive.sendsms.model.Location;

@Service
@Component("distributorService")
public class DistributorService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorService.class);

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
    public String addDistributor(Distributor distributor) throws ClassNotFoundException, SQLException {
    	/*ClassLoader classLoader = getClass().getClassLoader();
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
			}*/
			String sql = "INSERT INTO distributor (distributorName, distributorPhone, distributorLocationId) VALUES (?, ?, ?)";
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, distributor.getDistributorName());
			statement.setString(2, distributor.getDistributorPhone());
			statement.setInt(3, 1244);
			
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new distributor was inserted successfully!");
			}
            
			/*LOGGER.info("doc " + doc);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			LOGGER.info("source " + source);
			StreamResult result = new StreamResult(xmlFile);
			LOGGER.info("result " + result);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			LOGGER.info("XML file updated successfully");*/
            
        /*}catch(Exception e){
        	return e.getMessage();
        }*/
		return "distributor/request-get";
    }
    
    public List<Distributor> getDistributors() throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = new ArrayList<Distributor>();
		/*Document dom;
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
							distributor.setLocationId(location);
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
		}*/
		String sql = "SELECT * FROM distributor";
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
			Distributor distributor = new Distributor();
			distributor.setDistributorName(result.getString("distributorName"));
			distributor.setDistributorPhone(result.getString("distributorPhone"));
			distributor.setLocationId(result.getInt("distributorLocationId"));
			distributors.add(distributor);
		}
		return distributors;
	}

	public List<Location> getLocations() throws ClassNotFoundException, SQLException {
		return locationService.getLocations();
	}

	public void editDistributor(Distributor distributor) {

		
	}

}