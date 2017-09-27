package com.beatus.billlive.sendsms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cpfort on 9/15/16.
 */

@Controller
@RequestMapping("/")
public class ToolsController {

    private static final Logger LOG = LoggerFactory.getLogger(ToolsController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("message", "Billlive");
        model.addAttribute("title", "Billlive");
        return "welcome";
    }
}
