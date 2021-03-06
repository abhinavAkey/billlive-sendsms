package com.beatus.billlive.sendsms.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Implementation of {@link WebApplicationInitializer}. This initializer can
 * replace the {@code web.xml} file. If a {@code web.xml} file exists, those
 * definitions will be processed first, followed by Servlet Spec 3 annotated
 * classes, and then any definitions defined here.
 *
 * @author Abhinav Akey
 * @since 1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@SpringBootApplication(exclude = {JmxAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class ApplicationInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        
        // Create the Spring context.
        AnnotationConfigWebApplicationContext springContext
                = new AnnotationConfigWebApplicationContext(); 

        // Activate the apporpriate profile
        springContext.getEnvironment()
                .setActiveProfiles(
                        System.getProperty(
                                "com.beatus.billlive.env","default"));

        // Specify the spring configuration class 
        // and register the context listener
        springContext.register(ApplicationConfiguration.class); 
        context.addListener(new ContextLoaderListener(springContext));

     // Add the servlet mapping manually and make it initialize automatically
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
        ServletRegistration.Dynamic servlet = context.addServlet("SpringMvc", dispatcherServlet);

        servlet.addMapping("/");
        servlet.addMapping("/product");
        servlet.addMapping("/distributor");
        servlet.addMapping("/sms");
        servlet.addMapping("/location");
        servlet.addMapping("/user");
        
        servlet.setAsyncSupported(true);
        servlet.setLoadOnStartup(1);

        // Add the UTF8 CharacterEncodingFilter
        FilterRegistration.Dynamic characterEncodingFilter
                = context.addFilter(
                        "characterEncodingFilter",
                        CharacterEncodingFilter.class); 
        characterEncodingFilter.addMappingForUrlPatterns(
                null, 
                false, 
                "/*");
        characterEncodingFilter.setInitParameter(
                "encoding", 
                "UTF-8");
        characterEncodingFilter.setInitParameter(
                "forceEncoding", 
                "true"); 
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // Customize the application or call application.sources(...) to add sources
        // Since our example is itself a @Configuration class (via @SpringBootApplication)
        // we actually don't need to override this method.
        return application;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ApplicationInitializer.class, args);
    }
}
