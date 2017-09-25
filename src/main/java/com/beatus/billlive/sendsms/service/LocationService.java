package com.beatus.billlive.sendsms.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.repository.LocationRepository;

@Service
@Component("locationService")
public class LocationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);
	
	@Resource(name = "locationRepository")
	private LocationRepository locationRepository;
	
	public String addLocation(Location location) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addLocation");
		String resp = locationRepository.addLocation(location);
		return resp;
	}
	
	public String editLocation(Location location) throws SQLException {
		LOGGER.info("In editLocation");
		String resp = locationRepository.editLocation(location);
		return resp;	
	}

	public List<Location> getLocations() throws ClassNotFoundException, SQLException {
		LOGGER.info("In getLocations");
		List<Location> locations = locationRepository.getLocations();
		return locations;
	}
	public Location getLocationById(int locationId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getLocationById");
		Location location = locationRepository.getLocationById(locationId);
		return location;
	}
}