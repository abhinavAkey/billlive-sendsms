package com.beatus.billlive.sendsms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beatus.billlive.sendsms.service.SMSService;
import com.beatus.billlive.sendsms.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_SMS_REQUEST)
public class SMSController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

	@Resource(name = "smsService")
    private SMSService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public String smsHome(HttpServletRequest request, ModelMap model) {
        return "sms/home";
    }
    
    @RequestMapping(value = Constants.WEB_SMS_SEND_SMS_SCREEN,
            method = RequestMethod.GET)
    public String getProductsGet(HttpServletRequest request, ModelMap model) {
        //model.addAttribute("instance_info", itsService.getInstanceInfo());
        return "sms/request";
    }
}
