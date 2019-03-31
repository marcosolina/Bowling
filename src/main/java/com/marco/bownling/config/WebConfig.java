package com.marco.bownling.config;

import java.util.Collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@EnableWebMvc
@ComponentScan("com.marco")
/**
 * This class configures:
 * <ul>
 *  <li>Resource Handler for resources defined in the Webjar project</li>
 *  <li>Resource Handler for resources defined from the Current project</li>
 *  <li>Thymeleaf template resolver for templates defined in the Webjar project</li>
 *  <li>Thymeleaf template resolver for templates defined in the Current project</li>
 * </ul>
 * 
 * @author msolina
 *
 */
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    private static final String UTF8 = "UTF-8";

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    /**
     * It set up the Thymeleaf view Resolrver
     * 
     * @return The view resolver
     */
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding(UTF8);
        return resolver;
    }

    /**
     * Stuff for thymeleaf
     * @return The template Engine
     */
    private TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new LayoutDialect());
        engine.addTemplateResolver(localThymeleafTemplateResolverResolver());
        engine.addTemplateResolver(fromjar());
        return engine;
    }

    /**
     * This is the resolver for the Thymeleaf templates defined inside
     * the current project.
     * All your templates must go in in: /WEB-INF/view/
     * 
     * @return The template resolver for the local templates definitions
     */
    private ITemplateResolver localThymeleafTemplateResolverResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setOrder(2);
        resolver.setResolvablePatterns(Collections.singleton("view/*"));
        resolver.setPrefix("/WEB-INF/");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    /**
     * This is the resolver that retrieves the Thymeleaf templates
     * from the Webjar projects
     * 
     * @return The template resolver for the Webjar templates definitions
     */
    private ITemplateResolver fromjar() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setOrder(1);
        resolver.setPrefix("/templates/");
        resolver.setResolvablePatterns(Collections.singleton("external/*"));
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }
    
    /**
     * It makes available the resources from the current project and the 
     * one defined in the WebJar project.
     * To use the resources defined in:
     * <ul>
     *  <li>Current project: the path must start with /{AppName}/resources/**</li>
     *  <li>Webjar project: the path must start with /{AppName}/webjars/**</li>
     * </ul>
     */
     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/classes/web/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:META-INF/resources/webjars/");
    }

}