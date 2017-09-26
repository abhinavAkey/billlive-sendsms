package com.beatus.billlive.sendsms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.ProductsAndLocations;
import com.beatus.billlive.sendsms.utils.Constants;

@Component("locationRepository")
public class LocationRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocationRepository.class);

	@Autowired
	@Qualifier(value = "connection")
	private Connection conn;

	public String addLocation(Location location) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addLocation");
		Location locationAlreadyInDB = getLocationByLocationName(location.getLocationName());
		if (locationAlreadyInDB != null && StringUtils.isNotBlank(locationAlreadyInDB.getLocationName())) {
			location.setLocationId(locationAlreadyInDB.getLocationId());
			editLocation(location);
		} else {
			String sql = "INSERT INTO location (location_name, location_city, location_district, location_state) VALUES (?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, location.getLocationName());
			statement.setString(2, location.getLocationCity());
			statement.setString(3, location.getLocationDistrict());
			statement.setString(4, location.getLocationState());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new location was inserted successfully!");
			}
		}
		return Constants.REDIRECT + "/location/getLocations";
	}

	public List<Location> getLocations() throws ClassNotFoundException, SQLException {
		List<Location> locations = new ArrayList<Location>();

		String sql = "SELECT * FROM location";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()) {
			Location location = new Location();
			location.setLocationId(result.getInt("location_id"));
			location.setLocationName(result.getString("location_name"));
			location.setLocationState(result.getString("location_state"));
			location.setLocationCity(result.getString("location_city"));
			location.setLocationDistrict(result.getString("location_district"));
			locations.add(location);
		}
		return locations;
	}

	public Location getLocationById(int locationId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM location where location_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, locationId);
		ResultSet result = statement.executeQuery();
		Location location = new Location();
		while (result.next()) {
			location.setLocationId(result.getInt("location_id"));
			location.setLocationName(result.getString("location_name"));
			location.setLocationState(result.getString("location_state"));
			location.setLocationCity(result.getString("location_city"));
			location.setLocationDistrict(result.getString("location_district"));
		}
		return location;
	}

	public Location getLocationByLocationName(String locationName) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM location where location_name = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, locationName);
		ResultSet result = statement.executeQuery();
		Location location = new Location();
		while (result.next()) {
			location.setLocationId(result.getInt("location_id"));
			location.setLocationName(result.getString("location_name"));
			location.setLocationState(result.getString("location_state"));
			location.setLocationCity(result.getString("location_city"));
			location.setLocationDistrict(result.getString("location_district"));
		}
		return location;
	}

	public String editLocation(Location location) throws SQLException {
		String sql = "UPDATE location SET location_name= ?, location_city= ?, location_district= ?, location_state=? WHERE location_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, location.getLocationName());
		statement.setString(2, location.getLocationCity());
		statement.setString(3, location.getLocationDistrict());
		statement.setString(4, location.getLocationState());
		statement.setInt(5, location.getLocationId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new location was updated successfully!");
		}
		return Constants.REDIRECT + "/location/getLocations";
	}

}