package org.springboot;

import java.util.Arrays;

import org.springboot.config.WebAppInitializer;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan({"org.springboot"})
@EnableAutoConfiguration
@EnableJpaRepositories
@Import({WebAppInitializer.class})
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
