package org.springboot.config;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.DispatcherType;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springboot.SpringbootApplication;
import org.springboot.common.StaticContentServlet;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.NoNoise;
import com.google.code.kaptcha.servlet.KaptchaServlet;

@Configuration
@EnableWebMvc
public class WebInitializer extends WebMvcConfigurerAdapter implements WebApplicationInitializer{

    
	@Override
	public void onStartup(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ServletContextConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		container.addFilter("encodingFilter", characterEncodingFilter).addMappingForServletNames(null, false, "/*");
	
		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(ServletContextConfig.class);
		container.setInitParameter("spring.profiles.default", "production");
	
		
		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/**");
		
		container.addListener(ContextLoaderListener.class);
		
		
		OpenEntityManagerInViewFilter  openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
		container.addFilter("openEntityManagerInViewFilter", openEntityManagerInViewFilter).addMappingForServletNames(null, false, "/*");
	
		
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		delegatingFilterProxy.setTargetFilterLifecycle(true);
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(
                DispatcherType.REQUEST, DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR);
		container.addFilter("shiroFilter", delegatingFilterProxy).addMappingForServletNames(dispatcherTypes, false, "/*");
		container.addServlet("StaticContentServlet",new StaticContentServlet()).addMapping("/download");
		
//		
//		Map<String,String> properties =new HashMap<String,String>();
//    	properties.put("kaptcha.image.width", "70");
//    	properties.put("kaptcha.image.height", "35");
//    	properties.put("kaptcha.textproducer.char.length", "4");
//    	properties.put("kaptcha.textproducer.font.size", "30");
//    	properties.put("kaptcha.textproducer.char.string", "0123456789");
//    	properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
//    	ServletRegistration.Dynamic h2Servlet =  container.addServlet("kaptcha",new com.google.code.kaptcha.servlet.KaptchaServlet());//.setInitParameters(properties);
//    	h2Servlet.addMapping("/static/kaptcha.jpg");
//    	h2Servlet.setInitParameters(properties);
//    	   container.addServlet("kaptcha",new com.google.code.kaptcha.servlet.KaptchaServlet()).addMapping("static/kaptcha.jpg");//.setInitParameters(properties);
    	container.addListener(new SessionListener());
    	
    	
	}
//	 @Bean
//	  public ServletRegistrationBean kaptcha() {
//		 Map<String,String> properties =new HashMap<String,String>();
//	    	properties.put("kaptcha.image.width", "70");
//	    	properties.put("kaptcha.image.height", "35");
//	    	properties.put("kaptcha.textproducer.char.length", "4");
//	    	properties.put("kaptcha.textproducer.font.size", "30");
//	    	properties.put("kaptcha.textproducer.char.string", "0123456789");
//	    	properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
//	  ServletRegistrationBean servletRegistrationBean = new  ServletRegistrationBean(new KaptchaServlet(), "/static/kaptcha.jpg");
//	  servletRegistrationBean.setInitParameters(properties);
//	  return servletRegistrationBean;
//	  }
	 
	 
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    	registry.addResourceHandler("/home/**").addResourceLocations("/home/");
        if (!registry.hasMappingForPattern("/styles/**")) {
            registry.addResourceHandler("/styles/**").addResourceLocations(
                    "/static/styles/");
        }
        if (!registry.hasMappingForPattern("/images/**")) {
            registry.addResourceHandler("/images/**").addResourceLocations(
                    "/static/images/");
        }
        if (!registry.hasMappingForPattern("/fonts/**")) {
            registry.addResourceHandler("/fonts/**").addResourceLocations(
                    "/static/fonts/");
        }
        if (!registry.hasMappingForPattern("/js/**")) {
            registry.addResourceHandler("/js/**").addResourceLocations(
                    "/static/js/");
        }
    }
    
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// Note: this overwrites the default message converters.
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		MappingJackson2HttpMessageConverter  mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        converters.add(mappingJackson2HttpMessageConverter);
    }

}

