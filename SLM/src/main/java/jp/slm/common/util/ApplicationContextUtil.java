package jp.slm.common.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

public class ApplicationContextUtil implements ApplicationContextAware, ServletContextAware {
	
	private static ApplicationContextUtil INSTANCE = null;
	
	public static ApplicationContextUtil getInstance() {
		return INSTANCE;
	}
	
	public ApplicationContextUtil() {
		INSTANCE = this;
	}
	
	private ApplicationContext applicationContext;
	
	private ServletContext servletContext;
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
}
