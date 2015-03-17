package org.springboot;

import java.util.Arrays;

import org.springboot.config.ApplicationContext;
import org.springboot.config.ServletContextConfig;
import org.springboot.config.WebInitializer;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan({"org.springboot"})
@EnableAutoConfiguration
@Import({ApplicationContext.class, ServletContextConfig.class,WebInitializer.class})
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    	
//    	ApplicationContext ctx = (ApplicationContext) SpringApplication.run(SpringbootApplication.class,
//				args);
//
//		System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//		String[] beanNames = ((ListableBeanFactory) ctx).getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
    }
}
