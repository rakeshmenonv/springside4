/**
 * 
 */
package org.springboot.config;




import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Rajesh
 *
 */

//@ComponentScan(
//	    basePackages = {"com.infotop","org.seagatesoft.sde"},
//	    excludeFilters = @ComponentScan.Filter(
//	       value= Controller.class, 
//	       type = FilterType.ANNOTATION  org.springframework.web.bind.annotation.ControllerAdvice
//	    )
//	 )
@Configuration 
@ComponentScan(basePackages = { "com.infotop","org.seagatesoft.sde" },
excludeFilters = { @Filter(type = FilterType.ANNOTATION,
        value =Controller.class) , @Filter(type = FilterType.ANNOTATION,
                value =ControllerAdvice.class) })
@EnableWebMvc
@EnableTransactionManagement
public class ApplicationContext extends WebMvcConfigurerAdapter{
	

	    @Autowired
	    private DataSource dataSource;
//	    @Bean
//	    public AbstractEntityManagerFactoryBean  entityManagerFactoryRef() {
//	        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	        vendorAdapter.setDatabasePlatform(org.springside.modules.persistence.Hibernates.getDialect(dataSource));
//	        vendorAdapter.setShowSql(Boolean.TRUE);
//	        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//	        factory.setJpaVendorAdapter(vendorAdapter);
//	        factory.setPackagesToScan("org.springboot.webharvest");
//	        factory.setDataSource(dataSource);
//	        factory.afterPropertiesSet();
//	        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
//	        return factory;
//	 }
//
//	   @Bean
//	    public PlatformTransactionManager transactionManager() {
//	       
//	        JpaTransactionManager txManager = new JpaTransactionManager();
//	        txManager.setEntityManagerFactory(entityManagerFactoryRef().getObject());
//	        return txManager;
//	    }	
	   @Bean
	    public JdbcTemplate jdbcTemplate() {
	        return new JdbcTemplate(dataSource);
	    }
	   @Bean
	    public LocalValidatorFactoryBean validator() {
	        return new  LocalValidatorFactoryBean();
	    }


}
