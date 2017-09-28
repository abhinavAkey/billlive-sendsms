package com.beatus.billlive.sendsms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.utils.Constants;

@Component("locationRepository")
public class LocationRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocationRepository.class);

	@Autowired
	@Qualifier(value = "connection")
	private Connection conn;

	public String addLocation(Location location) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addLocation");
		Location locationAlreadyInDB = getLocationByLocationName(location.getLocationName(), location.getCompanyId());
		if (locationAlreadyInDB != null && StringUtils.isNotBlank(locationAlreadyInDB.getLocationName())) {
			location.setLocationId(locationAlreadyInDB.getLocationId());
			editLocation(location);
		} else {
			String sql = "INSERT INTO location (location_name, location_city, location_district, location_state, company_id, uid) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, location.getLocationName());
			statement.setString(2, location.getLocationCity());
			statement.setString(3, location.getLocationDistrict());
			statement.setString(4, location.getLocationState());
			statement.setString(5, location.getCompanyId());
			statement.setString(6, location.getUid());
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new location was inserted successfully!");
			}
		}
		return Constants.REDIRECT + "/location/getLocations";
	}

	public List<Location> getLocations(String companyId) throws ClassNotFoundException, SQLException {
		List<Location> locations = new ArrayList<Location>();

		String sql = "SELECT * FROM location WHERE company_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, companyId);
		ResultSet result = statement.executeQuery();
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

	public Location getLocationById(int locationId, String companyId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM location where location_id = ? AND company_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, locationId);
		statement.setString(2, companyId);

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

	public Location getLocationByLocationName(String locationName, String companyId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM location WHERE location_name = ? AND company_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, locationName);
		statement.setString(2, companyId);

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
		String sql = "UPDATE location SET location_name= ?, location_city= ?, location_district= ?, location_state=?, company_id = ?, uid = ?"
				+ " WHERE location_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, location.getLocationName());
		statement.setString(2, location.getLocationCity());
		statement.setString(3, location.getLocationDistrict());
		statement.setString(4, location.getLocationState());
		statement.setString(5, location.getCompanyId());
		statement.setString(6, location.getUid());
		statement.setInt(7, location.getLocationId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new location was updated successfully!");
		}
		return Constants.REDIRECT + "/location/getLocations";
	}

	public boolean deleteLocation(int locationId, String companyId) throws SQLException {
		String sql = "DELETE FROM location WHERE location_id = ? AND company_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, locationId);
		statement.setString(2, companyId);

		boolean result = statement.execute();
		return result;
	}

}