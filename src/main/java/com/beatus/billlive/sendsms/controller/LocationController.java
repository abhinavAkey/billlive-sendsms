package com.beatus.billlive.sendsms.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.model.LocationResponse;
import com.beatus.billlive.sendsms.service.LocationService;
import com.beatus.billlive.sendsms.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_LOCATION_REQUEST)
public class LocationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

	@Resource(name = "locationService")
    private LocationService locationService;

    @RequestMapping(method = RequestMethod.GET)
    public String locationHome(HttpServletRequest request, ModelMap model) {
        return "location/home";
    }
    
    @RequestMapping(value = Constants.WEB_LOCATION_ADD_LOCATION,
            method = RequestMethod.GET)
    public String addLocationGet(HttpServletRequest request, ModelMap model) {
        //model.addAttribute("instance_info", itsService.getInstanceInfo());
        return "location/request-add";
    }
    
    @RequestMapping(value = Constants.WEB_LOCATION_ADD_LOCATION,
            method = RequestMethod.POST)
    public String addLocationPost(HttpServletRequest request, Location location, ModelMap model) throws ClassNotFoundException, SQLException {
		String resp = locationService.addLocation(location);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_LOCATION_EDIT_LOCATION,
            method = RequestMethod.POST)
    public String editLocationPost(HttpServletRequest request, Location location, ModelMap model) throws SQLException {
		String resp = locationService.editLocation(location);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_LOCATION_GET_LOCATIONS,
            method = RequestMethod.GET)
    public String getLocationsGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	List<Location> locations = locationService.getLocations();
    	LOGGER.info("After the get call and the locations are "  + locations != null? locations.size() > 0 ? locations.get(0).getLocationName() : "No Location data" : "No Location data");
        LocationResponse resp = new LocationResponse();
        resp.setLocations(locations);
    	model.addAttribute("locations", locations);
        return "location/request-get";
    }
}
