package com.beatus.billlive.sendsms.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beatus.billlive.sendsms.model.User;
import com.beatus.billlive.sendsms.service.LoginService;
import com.beatus.billlive.sendsms.utils.Constants;
import com.beatus.billlive.sendsms.utils.CookieManager;

@Controller
@RequestMapping(Constants.WEB_USER_REQUEST)
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "loginService")
    private LoginService loginService;
	
	@Resource(name = "cookieManager")
	private CookieManager cookieManager;
	
	private static String COOKIE_NAME = "BL_SE";

    @RequestMapping(method = RequestMethod.GET)
    public String locationHome(HttpServletRequest request, ModelMap model) {
    	LOGGER.info("In LoginGet");
    	return "login/home";
    }
    
    @RequestMapping(value = Constants.WEB_LOGIN,
            method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request, ModelMap model) {
    	return "login/request-login";
    }
    
    @RequestMapping(value = Constants.WEB_LOGIN,
            method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, HttpServletResponse response, User user, ModelMap model) throws ClassNotFoundException, SQLException {
		String authenticatedResp = loginService.checkLogin(user, request, response);
		if(StringUtils.isNotBlank(authenticatedResp) && Constants.AUTHENTICATED.equalsIgnoreCase(authenticatedResp)){
			return Constants.REDIRECT + "/";
		}else {
			model.addAttribute("errorResp", authenticatedResp);
			return "login/request-login";
		}
    }
    
    @RequestMapping(value = Constants.WEB_USER_SIGNUP,
            method = RequestMethod.GET)
    public String signupGet(HttpServletRequest request, ModelMap model) {
    	return "login/request-signup";
    }
    
    @RequestMapping(value = Constants.WEB_LOGOUT,
            method = RequestMethod.GET)
    public String logoutGet(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
    	loginService.logoutUser(request, response);
    	return Constants.REDIRECT + "/user/login";
    }
    
    @RequestMapping(value = Constants.WEB_USER_SIGNUP,
            method = RequestMethod.POST)
    public String signupPost(HttpServletRequest request, User user, ModelMap model) throws ClassNotFoundException, SQLException {
		String signupResp = loginService.createUser(user, null);
		if(StringUtils.isNotBlank(signupResp) && Constants.USER_CREATED.equalsIgnoreCase(signupResp)){
			return Constants.REDIRECT + "/user/login";
		}else {
			model.addAttribute("errorResp", signupResp);
			return "login/request-signup";
		}
    }
    
    @RequestMapping(value = Constants.WEB_USER_ADD_COMPANY_USER,
            method = RequestMethod.POST)
    public String addCompanyUserPost(HttpServletRequest request, User user, ModelMap model) throws ClassNotFoundException, SQLException {
		String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String signupResp = loginService.addUserToCompany(user, companyId, uid);
		if(StringUtils.isNotBlank(signupResp) && Constants.USER_CREATED.equalsIgnoreCase(signupResp)){
			return Constants.REDIRECT + "/user/profile";
		}else {
			model.addAttribute("errorResp", signupResp);
			return "login/request-signup";
		}
    }
}
