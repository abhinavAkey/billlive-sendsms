package com.beatus.billlive.sendsms.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beatus.billlive.sendsms.model.ProductWithLocationsAndPricesRequest;
import com.beatus.billlive.sendsms.service.SMSService;
import com.beatus.billlive.sendsms.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_SMS_REQUEST)
public class SMSController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SMSController.class);

	@Resource(name = "smsService")
    private SMSService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public String smsHome(HttpServletRequest request, ModelMap model) {
        return "sms/home";
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_SCREEN,
            method = RequestMethod.GET)
    public String getSMSScreen(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	smsService.getSMSScreen(request, model);
        return "sms/request";
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_SCREEN,
            method = RequestMethod.POST)
    public String getSMSScreenPOST(HttpServletRequest request, ProductWithLocationsAndPricesRequest productNameLocAndPrice, ModelMap model) throws ClassNotFoundException, SQLException {
    	LOGGER.info("productNameLocAndPrice" + productNameLocAndPrice.getProductNameLocAndPrice());
    	smsService.postSMSScreen(request, productNameLocAndPrice, model);
        return Constants.REDIRECT + "/sms/sendsmsScreen";
    }
}
