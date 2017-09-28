package com.beatus.billlive.sendsms.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.billlive.sendsms.model.Distributor;
import com.beatus.billlive.sendsms.model.Location;
import com.beatus.billlive.sendsms.repository.DistributorRepository;
import com.beatus.billlive.sendsms.utils.Constants;

@Service
@Component("distributorService")
public class DistributorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorService.class);

	@Resource(name = "locationService")
	private LocationService locationService;
	
	@Resource(name = "distributorRepository")
	private DistributorRepository distributorRepository;

	public String addDistributor(Distributor distributor, String companyId, String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addDistributor");
		distributor.setCompanyId(companyId);
		distributor.setUid(uid);
		distributorRepository.addDistributor(distributor);
		return Constants.REDIRECT + "/distributor/getDistributors";
	}
	
	public String editDistributor(Distributor distributor, String companyId, String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In editDistributor");
		distributor.setCompanyId(companyId);
		distributor.setUid(uid);
		distributorRepository.editDistributor(distributor);
		return Constants.REDIRECT + "/distributor/getDistributors";
	}
	
	public Distributor getDistributorById(int id, String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorById");
		Distributor distributor = distributorRepository.getDistributorById(id, companyId);
		return distributor;
	}

	public List<Distributor> getDistributors(String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributors");
		List<Distributor> distributors = distributorRepository.getDistributors(companyId);
		return distributors;
	}

	public List<Location> getLocations(String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getLocations");
		return locationService.getLocations(companyId);
	}

	public String deleteDistributor(int distributorId, String companyId, String uid) throws SQLException {
		LOGGER.info("In deleteDistributor");

		boolean isDeleted = distributorRepository.deleteDistributor(distributorId, companyId);
		if(isDeleted){
			return Constants.REDIRECT + "/distributor/getDistributors";
		}else {
			//TO-DO add a message that the row is not deleted
			return Constants.REDIRECT + "/distributor/getDistributors";
		}
			
	}

}