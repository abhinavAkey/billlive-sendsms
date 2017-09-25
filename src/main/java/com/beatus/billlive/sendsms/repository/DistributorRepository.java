package com.beatus.billlive.sendsms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

		String sql = "INSERT INTO distributor (distributor_name, distributo_phone, location_id) VALUES (?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, distributor.getDistributorName());
		statement.setString(2, distributor.getDistributorPhone());
		statement.setInt(3, distributor.getLocationId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was inserted successfully!");
		}
		return Constants.REDIRECT + "/distributor/getDistributors";
	}

	public String editDistributor(Distributor distributor) throws ClassNotFoundException, SQLException {

		String sql = "UPDATE distributor SET distributor_name= ?, distributo_phone= ?, location_id= ? WHERE distributor_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, distributor.getDistributorName());
		statement.setString(2, distributor.getDistributorPhone());
		statement.setInt(3, distributor.getLocationId());
		statement.setInt(4, distributor.getDistributorId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new distributor was updated successfully!");
		}
		return Constants.REDIRECT + "/distributor/getDistributors";
	}

	public Distributor getDistributorById(int id) throws ClassNotFoundException, SQLException {
		Distributor distributor = new Distributor();
		String sql = "SELECT dist.distributor_name AS distributorName, dist.distributor_phone AS distributorPhone, loc.location_id AS distributorLocationId, loc.location_name AS distributorLocationName FROM distributor dist, location loc WHERE distributor_id = ? AND dist.location_id=loc.location_id";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			distributor.setDistributorName(result.getString("distributorName"));
			distributor.setDistributorPhone(result.getString("distributorPhone"));
			distributor.setLocationId(result.getInt("distributorLocationId"));
			distributor.setDistributorLocation(result.getString("distributorLocationName"));
			
		}
		return distributor;
	}

	public List<Distributor> getDistributors() throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = new ArrayList<Distributor>();
		String sql = "SELECT dist.distributor_name AS distributorName, dist.distributor_phone AS distributorPhone, loc.location_id AS distributorLocationId, loc.location_name AS distributorLocationName FROM distributor dist, location loc WHERE dist.location_id=loc.location_id";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()) {
			Distributor distributor = new Distributor();
			distributor.setDistributorName(result.getString("distributorName"));
			distributor.setDistributorPhone(result.getString("distributorPhone"));
			distributor.setLocationId(result.getInt("distributorLocationId"));
			distributor.setDistributorLocation(result.getString("distributorLocationName"));
			distributors.add(distributor);
		}
		return distributors;
	}
}
