package org.springboot.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springboot.system.account.service.AccountService;
import org.springboot.system.account.service.CaptchaFormAuthenticationFilter;
import org.springboot.system.account.service.ShiroDbRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.Filter;
@Configuration
@EnableWebMvc
@ImportResource({"classpath:applicationContext-shiro.xml"})
public class ServletContextConfig extends SpringBootServletInitializer {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:messageResource");
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver sessionLocaleResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en_US"));

		return localeResolver;
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

//
//	@Bean(name = "shiroFilter")
//	public ShiroFilterFactoryBean shiroFilter() {
//	    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//	    shiroFilter.setLoginUrl("/login");
//	    shiroFilter.setSuccessUrl("/index");
//	    shiroFilter.setUnauthorizedUrl("/forbidden");
//	    Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
//	    filterChainDefinitionMapping.put("/", "anon");
//	    filterChainDefinitionMapping.put("/home", "authc,roles[guest]");
//	    filterChainDefinitionMapping.put("/admin", "authc,roles[admin]");
//	    shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
//	    shiroFilter.setSecurityManager(securityManager());
//	    Map<String, Filter> filters = new HashMap<String, Filter>();
//	    filters.put("anon", new AnonymousFilter());
//	    filters.put("authc", new FormAuthenticationFilter());
//	    filters.put("logout", new LogoutFilter());
//	    filters.put("roles", new RolesAuthorizationFilter());
//	    filters.put("user", new UserFilter());
//	    shiroFilter.setFilters(filters);
//	    System.out.println(shiroFilter.getFilters().size());
//	    return shiroFilter;
//	}
//
//	@Bean(name = "securityManager")
//	public SecurityManager securityManager() {
//	    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//	    securityManager.setRealm(realm());
//	    return securityManager;
//	}
//
//	@Bean(name = "realm")
//	@DependsOn("lifecycleBeanPostProcessor")
//	public ShiroDbRealm shiroDbRealm() {
//		ShiroDbRealm shiroDbRealm = new ShiroDbRealm();
//		AccountService accountService = new AccountService();
//		shiroDbRealm.setAccountService(accountService);
//		return shiroDbRealm;
//	}
//
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//	    return new LifecycleBeanPostProcessor();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	@Bean
//	public CaptchaFormAuthenticationFilter captchaFormAuthenticationFilter() {
//		return new CaptchaFormAuthenticationFilter();
//	}
//
//	@Bean(name="lifecycleBeanPostProcessor")
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}
//
//	@Bean
//	@DependsOn("lifecycleBeanPostProcessor")
//	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		advisorAutoProxyCreator.setProxyTargetClass(true);
//		return advisorAutoProxyCreator;
//	}
//	@Bean
//	public ShiroDbRealm shiroDbRealm() {
//		ShiroDbRealm shiroDbRealm = new ShiroDbRealm();
//		AccountService accountService = new AccountService();
//		shiroDbRealm.setAccountService(accountService);
//		return shiroDbRealm;
//	}
//	
//	@Bean
//	public WebSecurityManager  securityManager() {
//		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//		defaultWebSecurityManager.setRealm(shiroDbRealm());
//		return defaultWebSecurityManager;
//	}
//
//	@Bean
//	public ShiroFilterFactoryBean shiroFilter() {
//		ShiroFilterFactoryBean  shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//	
//		shiroFilterFactoryBean.setSecurityManager(securityManager());
//		shiroFilterFactoryBean.setLoginUrl("/login");
//		shiroFilterFactoryBean.setSuccessUrl("/home");
//		Map<String,Filter> filters = new HashMap<String,Filter>();
//		filters.put("authc", captchaFormAuthenticationFilter());
//		shiroFilterFactoryBean.setFilters(filters);
//		Map<String, String> definitionsMap = new HashMap<String, String>();
//        definitionsMap.put("/login", "authc");
//        definitionsMap.put("/logout", "logout");
//        definitionsMap.put("/static/**", "anon");
//        definitionsMap.put("/register/**", "anon");
//        definitionsMap.put("/**", "user");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(definitionsMap);
//        System.out.println("##########"+shiroFilterFactoryBean.getSecurityManager());
//		return shiroFilterFactoryBean;
//	}
//	
//	@Bean
//    public MethodInvokingFactoryBean methodInvokingFactoryBean(){
//        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
//        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//        methodInvokingFactoryBean.setArguments(new Object[]{securityManager()});
//        return methodInvokingFactoryBean;
//    }
	
	
	
	
//	<!-- Shiro Filter -->
//	<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
//		<property name="securityManager" ref="securityManager" />
//		<property name="loginUrl" value="/login" />
//		<property name="successUrl" value="/home" />
//		<property name="filters">
//			<map>
//				<entry key="authc" value-ref="captchaFormAuthenticationFilter" />
//			</map>
//		</property>
//		<property name="filterChainDefinitions">
//			<value>
//				/login = authc
//				/logout = logout
//				/static/** = anon
//				/register/** = anon
//				/** = user
//			</value>
//		</property>
}
