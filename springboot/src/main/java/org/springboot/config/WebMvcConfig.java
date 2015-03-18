package org.springboot.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Equivalent to &lt;mvc:annotation:driven/&gt; . Uses
 * {@code DelegatingWebMvcConfiguration}. Implement {@code WebMvcConfigurer} or
 * extend {@code WebMvcConfigurerAdapter}.
 * 
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * 
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
 * 
 * @author <a href="http://rockhoppertech.com/blog/">Gene De Lisa</a>
 * 
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.springboot" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final String MESSAGE_SOURCE = "classpath:messageResource";

	private static final Logger logger = LoggerFactory
			.getLogger(WebMvcConfig.class);

	/**
	 * @return the view resolver
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
        converters.add(converter());
      //  addDefaultHttpMessageConverters(converters);
    }

    @Bean
    MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
	    return converter;
    }
    
	@Bean
	public ViewResolver viewResolver() {
		logger.debug("setting up view resolver");

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource() {
		logger.debug("setting up message source");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE);
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver lr = new org.springframework.web.servlet.i18n.SessionLocaleResolver();
		lr.setDefaultLocale(Locale.ENGLISH);
		return lr;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.debug("setting up resource handlers");
		registry.addResourceHandler("/resources/").addResourceLocations(
				"/resources/**");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		logger.debug("configureDefaultServletHandling");
		// if the spring dispatcher is mapped to / then forward non handled
		// requests
		// (e.g. static resource) to the container's "default servlet"
		configurer.enable();
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
	    StringHttpMessageConverter converter = new StringHttpMessageConverter();
	    converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
	    return converter;
	}

	/*@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();

		Properties mappings = new Properties();
		mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
		mappings.put("org.springframework.dao.DataAccessException",
				"dataAccessFailure");
		mappings.put("org.springframework.transaction.TransactionException",
				"dataAccessFailure");
		b.setExceptionMappings(mappings);
		return b;
	}*/

}
