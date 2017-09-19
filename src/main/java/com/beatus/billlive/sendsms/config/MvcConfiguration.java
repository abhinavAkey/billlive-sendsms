package com.beatus.billlive.sendsms.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Configuration class for MVC. Sets up things like view resolvers, resource
 * mappings, and controllers. This class leverages Spring's 
 * {@link WebMvcConfigurerAdapter} which provides convenience methods for 
 * setting up Spring MVC.
 * 
 * @author Abhinav Akey
 */
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	private final static String REAL_CSS_PATH = "/resources/css/";

    private final static String REAL_JS_PATH = "/resources/js/";

    private final static String REAL_ASSETS_PATH = "/resources/assets/";

    private final static String CDN_BASE_KEY = "cdnBase";

    @Value("/resources/css/*")
    private String versionedCssPath;

    @Value("/resources/js/*")
    private String versionedJsPath;

    @Value("/resources/assets/*")
    private String versionedAssetsPath;

    @Value("${mvc.cdnBase:}")
    private String cdnBase;


    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        return freeMarkerConfigurer;
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    /**
     * Adds resource handlers to handle versioned static paths. This will
     * allow the application server to be the Origin Server for statics and
     * send "cache forever" cache directives as the paths will change on each
     * deployment.
     *
     * @param registry the ResourceHandlerRegisty managed by the super class
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(versionedCssPath).addResourceLocations(REAL_CSS_PATH)
            .setCachePeriod(Integer.MAX_VALUE);

        registry.addResourceHandler(versionedJsPath).addResourceLocations(REAL_JS_PATH)
            .setCachePeriod(Integer.MAX_VALUE);

        registry.addResourceHandler(versionedAssetsPath).addResourceLocations(REAL_ASSETS_PATH)
            .setCachePeriod(Integer.MAX_VALUE);
    }

    /**
     * Properties bean that will be exposed in all views. Includes all build
     * properties (and selective others).
     *
     * @return PropertiesFactoryBean that will create the properties bean
     */
    @Bean(name = "viewProperties")
    public PropertiesFactoryBean viewProperties() {
        PropertiesFactoryBean viewProperties = new PropertiesFactoryBean();
        viewProperties.setLocation(new ClassPathResource("build.properties"));

        Properties p = new Properties();

        p.setProperty(CDN_BASE_KEY, cdnBase);

        viewProperties.setProperties(p);

        return viewProperties;
    }
}
