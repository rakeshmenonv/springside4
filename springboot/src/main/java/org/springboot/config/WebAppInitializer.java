package org.springboot.config;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.common.StaticContentServlet;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Servlet 3.0 replacement for web.xml
 * 
 * @author <a href="http://rockhoppertech.com/blog/">Gene De Lisa</a>
 * 
 */
@SpringBootApplication
@ImportResource({"classpath:applicationContext-shiro.xml"})
public class WebAppInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory
			.getLogger(WebAppInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		delegatingFilterProxy.setTargetFilterLifecycle(true);
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(
                DispatcherType.REQUEST, DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR);
		servletContext.addFilter("shiroFilter", delegatingFilterProxy).addMappingForServletNames(dispatcherTypes, false, "/*");
		servletContext.addListener(new SessionListener());

	}


}
