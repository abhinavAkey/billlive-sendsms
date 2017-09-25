package com.beatus.billlive.sendsms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.beatus.billlive.sendsms.model.SMSConfiguration;
import com.beatus.billlive.sendsms.utils.Constants;

@Component("smsRepository")
public class SMSRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(SMSRepository.class);
	
	@Autowired
    @Qualifier(value = "connection")
	private Connection conn;

	public void addSMSConfiguration(SMSConfiguration config) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addSMS");
		
		String sql = "INSERT INTO sms_configuration (sms_url, parameter_username, parameter_password, "
				+ "send_code, message_header, message_footer) VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, config.getSmsUrl());
		statement.setString(2, config.getParameterUsername());
		statement.setString(3, config.getParameterPassword());
		statement.setString(4, config.getSendCode());
		statement.setString(5, config.getMessageHeader());
		statement.setString(6, config.getMessageFooter());
		
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new sms was inserted successfully!");
		}

	}

	public SMSConfiguration getSMSConfiguration() throws ClassNotFoundException, SQLException {
		SMSConfiguration config = null;
		String sql = "SELECT * FROM sms";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
			config = new SMSConfiguration();
			config.setConfigurationId(result.getInt("configuration_id"));
			config.setSmsUrl(result.getString("sms_url"));
			config.setParameterUsername(result.getString("parameter_username"));
			config.setParameterPassword(result.getString("parameter_password"));
			config.setSendCode(result.getString("send_code"));
			config.setMessageHeader(result.getString("message_header"));
			config.setMessageFooter(result.getString("message_footer"));
			break;
		}
		return config;
	}
	
	public void editSMSConfiguration(SMSConfiguration config) throws SQLException {
		String sql = "UPDATE sms_configuration SET sms_url = ?, parameter_username = ?, parameter_password = ?, "
				+ "send_code = ?, message_header = ?, message_footer = ? WHERE configuration_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, config.getSmsUrl());
		statement.setString(2, config.getParameterUsername());
		statement.setString(3, config.getParameterPassword());
		statement.setString(4, config.getSendCode());
		statement.setString(5, config.getMessageHeader());
		statement.setString(6, config.getMessageFooter());
		statement.setInt(7, config.getConfigurationId());
		
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new sms was updated successfully!");
		}
	}

}