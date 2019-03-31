package com.marco.bownling.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.marco.bownling.utils.BowlingWebAppConstants;
import com.marco.bownling.utils.BownlingWebAppUtils;
import com.marco.utils.MarcoException;
import com.marco.utils.miscellaneous.MarcoUtils;
import com.marco.webjar.MarcoWebJarUtils;

/**
 * This class initialise:
 * <ul>
 *  <li>Spring</li>
 *  <li>Utils jar</li>
 *  <li>Webjar jar</li>
 * </ul>
 * 
 * @author msolina
 *
 */
public class SpringWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        context.setServletContext(servletContext);
        // Spring MVC front controller
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        
        /*
         * Setting the properties required by the WebJar project
         */
        MarcoUtils.setPropertiesFileName(BowlingWebAppConstants.PROPERTIES_FILE);
        try {
            MarcoWebJarUtils.APP_NAME = MarcoUtils.getProperty(BowlingWebAppConstants.PRP_APP_NAME);
            MarcoWebJarUtils.APP_BUILD = MarcoUtils.getProperty(BowlingWebAppConstants.PRP_APP_BUILD);
            MarcoWebJarUtils.APP_VERSION = MarcoUtils.getProperty(BowlingWebAppConstants.PRP_APP_VERSION);
        } catch (MarcoException e) {
            e.printStackTrace();
        }
        MarcoWebJarUtils.APP_URLS = BownlingWebAppUtils.getJsUrlConstants();
    }
}