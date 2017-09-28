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

import com.beatus.billlive.sendsms.model.Distributor;
import com.beatus.billlive.sendsms.utils.Constants;

@Component("distributorRepository")
public class DistributorRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorRepository.class);

	@Autowired
	@Qualifier(value = "connection")
	private Connection conn;

	public String addDistributor(Distributor distributor) throws ClassNotFoundException, SQLException {

		Distributor distributorFromDB = getDistributorByDistributorName(distributor.getDistributorName(), distributor.getCompanyId());
		if (distributorFromDB != null && StringUtils.isNotBlank(distributorFromDB.getDistributorName())) {
			distributor.setDistributorId(distributorFromDB.getDistributorId());
			editDistributor(distributor);
		} else {
			String sql = "INSERT INTO distributor (distributor_name, distributor_phone, location_id, company_id, uid) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, distributor.getDistributorName());
			statement.setString(2, distributor.getDistributorPhone());
			statement.setInt(3, distributor.getLocationId());
			statement.setString(4, distributor.getCompanyId());
			statement.setString(5, distributor.getUid());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new distributor was inserted successfully!");
			}
		}
		return Constants.REDIRECT + "/distributor/getDistributors";
	}

	public String editDistributor(Distributor distributor) throws ClassNotFoundException, SQLException {

		String sql = "UPDATE distributor SET distributor_name= ?, distributor_phone= ?, location_id= ?, company_id = ?, uid = ? WHERE distributor_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, distributor.getDistributorName());
		statement.setString(2, distributor.getDistributorPhone());
		statement.setInt(3, distributor.getLocationId());
		statement.setString(4, distributor.getCompanyId());
		statement.setString(5, distributor.getUid());
		statement.setInt(6, distributor.getDistributorId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was updated successfully!");
		}
		return Constants.REDIRECT + "/distributor/getDistributors";
	}

	public Distributor getDistributorById(int id, String companyId) throws ClassNotFoundException, SQLException {
		Distributor distributor = new Distributor();
		String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_name AS distributorName, dist.distributor_phone AS distributorPhone, "
				+ "loc.location_id AS distributorLocationId, loc.location_name AS distributorLocationName "
				+ "FROM distributor dist, location loc "
				+ "WHERE distributor_id = ? AND dist.location_id=loc.location_id AND dist.company_id = loc.company_id "
				+ "AND dist.company_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		statement.setString(2, companyId);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			distributor.setDistributorId(result.getInt("distributorId"));
			distributor.setDistributorName(result.getString("distributorName"));
			distributor.setDistributorPhone(result.getString("distributorPhone"));
			distributor.setLocationId(result.getInt("distributorLocationId"));
			distributor.setDistributorLocation(result.getString("distributorLocationName"));

		}
		return distributor;
	}
	
	public Distributor getDistributorByDistributorName(String name, String companyId) throws ClassNotFoundException, SQLException {
		Distributor distributor = new Distributor();
		String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_name AS distributorName, dist.distributor_phone AS distributorPhone, "
				+ "loc.location_id AS distributorLocationId, loc.location_name AS distributorLocationName "
				+ "FROM distributor dist, location loc "
				+ "WHERE distributor_name = ? AND dist.location_id=loc.location_id AND dist.company_id = loc.company_id "
				+ "AND dist.company_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, companyId);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			distributor.setDistributorId(result.getInt("distributorId"));
			distributor.setDistributorName(result.getString("distributorName"));
			distributor.setDistributorPhone(result.getString("distributorPhone"));
			distributor.setLocationId(result.getInt("distributorLocationId"));
			distributor.setDistributorLocation(result.getString("distributorLocationName"));

		}
		return distributor;
	}

	public List<Distributor> getDistributors(String companyId) throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = new ArrayList<Distributor>();
		String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_name AS distributorName, dist.distributor_phone AS distributorPhone,"
				+ " loc.location_id AS distributorLocationId, loc.location_name AS distributorLocationName "
				+ "FROM distributor dist, location loc "
				+ "WHERE dist.location_id=loc.location_id AND dist.company_id = loc.company_id "
				+ "AND dist.company_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, companyId);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			Distributor distributor = new Distributor();
			distributor.setDistributorId(result.getInt("distributorId"));
			distributor.setDistributorName(result.getString("distributorName"));
			distributor.setDistributorPhone(result.getString("distributorPhone"));
			distributor.setLocationId(result.getInt("distributorLocationId"));
			distributor.setDistributorLocation(result.getString("distributorLocationName"));
			distributors.add(distributor);
		}
		return distributors;
	}

	public boolean deleteDistributor(int distributorId, String companyId) throws SQLException {
		String sql = "DELETE FROM distributor where distributor_id = ? AND company_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, distributorId);
		statement.setString(2, companyId);

		boolean result = statement.execute();
		return result;
	}
}
