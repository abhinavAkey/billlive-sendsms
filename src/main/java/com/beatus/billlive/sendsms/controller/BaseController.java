package com.beatus.billlive.sendsms.controller;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by cpfort on 3/5/17.
 */
public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private BeansWrapper beansWrapper = new BeansWrapper();
    protected TemplateHashModel statics = beansWrapper.getStaticModels();

    protected void logIncomingParameters(HttpServletRequest request, Logger logger) {
        try {
            String uri = request.getRequestURI();
            logger.debug("Requested Resource:" + uri);

            Enumeration<String> enumeration = request.getParameterNames();

            while (enumeration.hasMoreElements()) {
                String parametername = enumeration.nextElement();
                logger.debug(parametername + " : " + request.getParameter(parametername));
            }
        } catch (Exception e) {
            logger.error("" + e);
        }
    }

}
