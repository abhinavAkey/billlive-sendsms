package com.beatus.billlive.sendsms.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.beatus.billlive.sendsms.encryption.EncryptionFactory;
import com.beatus.billlive.sendsms.encryption.HashFactory;
import com.beatus.billlive.sendsms.encryption.HashFactory.Hash;
import com.beatus.billlive.sendsms.encryption.KeyChainEntries;
import com.beatus.billlive.sendsms.model.User;
import com.beatus.billlive.sendsms.repository.LoginRepository;
import com.beatus.billlive.sendsms.utils.Constants;
import com.beatus.billlive.sendsms.utils.CookieManager;

@Component("loginService")
public class LoginService {

	@Resource(name = "loginRepository")
	LoginRepository loginRepository;

	@Resource(name = "keyChainEntries")
	private KeyChainEntries keyChainEntries;

	@Resource(name = "cookieManager")
	private CookieManager cookieManager;

	private static String COOKIE_NAME = "BL_SE";

	private static final byte[] KEY = { (byte) 0x1C, (byte) 0x33, (byte) 0x18, (byte) 0x63, (byte) 0xC8, (byte) 0xA4,
			(byte) 0x3F, (byte) 0xD2, (byte) 0x30, (byte) 0x08, (byte) 0x0F, (byte) 0xC7, (byte) 0xA4, (byte) 0xB0,
			(byte) 0x48, (byte) 0x26 };

	public String checkLogin(User user, HttpServletRequest request, HttpServletResponse response) {
		String loginResp;
		try {
			User dbUser = loginRepository.getUserByUsername(user.getUsername());
			if (dbUser != null && StringUtils.isNotBlank(dbUser.getPassword())) {
				byte[] encBytes = Base64.decodeBase64(dbUser.getPassword());
				byte[] encryptionKey = keyChainEntries.getAesKeyBytes();
				SecretKeySpec encKey = new SecretKeySpec(encryptionKey, Constants.AES);
				EncryptionFactory.Encryption enc = EncryptionFactory.getInstance(encKey.getAlgorithm());
				byte[] idBytes = enc.decrypt(encKey.getEncoded(), encBytes);
				String decryptedPassword = new String(idBytes, Constants.CHAR_SET);

				byte[] hashedPasswordDB = Base64.decodeBase64(decryptedPassword);
				Hash passwordHash = HashFactory.getInstance(Constants.HMACSHA256);
				boolean isPasswordMatch = passwordHash.match(KEY, user.getPassword().getBytes(), hashedPasswordDB);
				if (isPasswordMatch) {
					return Constants.AUTHENTICATED;
				} else {
					return Constants.ERROR_LOGIN;
				}
			} else {
				return Constants.ERROR_LOGIN;
			}
		} catch (ClassNotFoundException | UnsupportedEncodingException | SQLException e) {
			loginResp = Constants.ERROR_LOGIN;
		}
		return loginResp;
	}

	public String createUser(User user) {
		String loginResp;
		try {
			Hash passwordHash = HashFactory.getInstance(Constants.HMACSHA256);
			byte[] hashedPassword = passwordHash.hash(KEY, user.getPassword().getBytes());
			String macString = Base64.encodeBase64URLSafeString(hashedPassword);

			// Encrypt teh password
			byte[] encryptionKey = keyChainEntries.getAesKeyBytes();
			SecretKeySpec secretKey = new SecretKeySpec(encryptionKey, Constants.AES);
			EncryptionFactory.Encryption enc = EncryptionFactory.getInstance(secretKey.getAlgorithm());
			byte[] encBytes = enc.encrypt(secretKey.getEncoded(), macString.getBytes(Constants.CHAR_SET));
			String encPasswordStringWithHash = Base64.encodeBase64URLSafeString(encBytes);

			user.setPassword(encPasswordStringWithHash);

			loginResp = loginRepository.addUser(user);
		} catch (ClassNotFoundException | UnsupportedEncodingException | SQLException e) {
			loginResp = Constants.ERROR_USER_CREATION;
		}
		return loginResp;
	}

	public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		cookieManager.addCookie(response, COOKIE_NAME, "", false, true);
	}

}
