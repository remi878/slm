package jp.slm.web.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import jp.slm.business.bean.SessionLog;
import jp.slm.business.service.SessionLogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionLogListener implements HttpSessionListener {
	
	public static final Logger LOG = LoggerFactory.getLogger(SessionLogListener.class);
	
	public static int count = 0;
	
	@Autowired
	private SessionLogService sessionLogService;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		LOG.info("nb active session = " + count);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		if (se.getSession().getAttribute(SessionLog.class.getName()) != null && sessionLogService != null) {
			saveSessionLog((SessionLog) se.getSession().getAttribute(SessionLog.class.getName()));
		}
	}
	
	private void saveSessionLog(SessionLog sessionLog) {
		sessionLog.setEnd(new Date());
		sessionLogService.create(sessionLog);
	}
	
}
