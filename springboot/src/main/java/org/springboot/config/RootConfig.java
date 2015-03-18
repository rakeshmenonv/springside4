package org.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Configuration classes =~ <beans/> xml documents
 * @author <a href="http://rockhoppertech.com/blog/">Gene De Lisa</a>
 * 
 */
@Configuration
@ImportResource({"classpath:applicationContext-shiro.xml"})
@ComponentScan(basePackages = { "org.springboot"})
public class RootConfig {

	// @Bean methods ~= <bean/> elements

}