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

import com.beatus.billlive.sendsms.model.Distributor;
import com.beatus.billlive.sendsms.model.DistributorResponse;
import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.service.DistributorService;
import com.beatus.billlive.sendsms.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_DISTRIBUTOR_REQUEST)
public class DistributorsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorsController.class);

	@Resource(name = "distributorService")
    private DistributorService distributorService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String distributorHome(HttpServletRequest request, ModelMap model) {
        return "distributor/home";
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_DISTRIBUTOR,
            method = RequestMethod.GET)
    public String addDistributorGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	List<Location> locations = distributorService.getLocations(companyId);
        model.addAttribute("locations", locations);
        return "distributor/request-add";
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_DISTRIBUTOR,
            method = RequestMethod.POST)
    public String addDistributorPost(HttpServletRequest request, Distributor distributor, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = distributorService.addDistributor(distributor, companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_EDIT_DISTRIBUTOR,
            method = RequestMethod.POST)
    public String editDistributorPost(HttpServletRequest request, Distributor distributor, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = distributorService.editDistributor(distributor, companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_DELETE_DISTRIBUTOR,
            method = RequestMethod.POST)
    public String deleteDistributorPost(HttpServletRequest request, Distributor distributor, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = distributorService.deleteDistributor(distributor.getDistributorId(), companyId, uid);
    	return resp;
    }
    
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_DISTRIBUTORS,
            method = RequestMethod.GET)
    public String getDistributorsGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	List<Distributor> distributors = distributorService.getDistributors(companyId);
    	LOGGER.info("After the get call and the distributors are "  + distributors != null? distributors.size() > 0 ? distributors.get(0).getDistributorName() : "No Distributor data" : "No Distributor data");
        DistributorResponse resp = new DistributorResponse();
        resp.setDistributors(distributors);
    	model.addAttribute("distributors", distributors);
        return "distributor/request-get";
    }
}
