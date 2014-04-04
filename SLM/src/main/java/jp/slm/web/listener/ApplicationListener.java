package jp.slm.web.listener;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.slm.web.controller.generic.GenericController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationListener implements ServletContextListener {
	
	public static final Logger LOG = LoggerFactory.getLogger(ApplicationListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		Class<?> clazz = GenericController.class;
		List<Field> constFields = new ArrayList<Field>();
		for (Field field : clazz.getFields()) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) && Modifier.isFinal(mod) && Modifier.isPublic(mod)) {
				constFields.add(field);
			}
		}
		for (Field field : constFields) {
			try {
				sc.setAttribute(field.getName(), field.get(null));
			} catch (Exception e) {
				LOG.error("Error reading constant field: ", e);
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
}
