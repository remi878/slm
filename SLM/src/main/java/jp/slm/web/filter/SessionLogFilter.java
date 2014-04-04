package jp.slm.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.slm.business.bean.SessionLog;
import jp.slm.business.service.SessionLogService;
import jp.slm.web.controller.generic.GenericController;
import jp.slm.web.util.RemoteInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

public class SessionLogFilter extends OncePerRequestFilter {
	
	public static final Logger LOG = LoggerFactory.getLogger(SessionLogFilter.class);
	
	private final static String[] RESSOURCE_EXTS = new String[] { ".css", ".js", ".jpg", ".jpeg", ".png", ".cur", "/img/", "/css/", "/js/", "/static/", "/misc/", "/font/" };
	
	private static final long LOGIN_THRESHOLD_TIMEOUT = 5 * 60 * 1000; // 5 min
	
	private static final int LOGIN_THRESHOLD = 10;
	
	private static final int SESSION_IP_THRESHOLD_10S = 5;
	
	private static final int SESSION_IP_THRESHOLD_1MIN = 10;
	
	private static final int SESSION_IP_THRESHOLD_30MIN = 15;
	
	@Autowired
	private SessionLogService sessionLogService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		long timer = System.currentTimeMillis();
		SessionLog sessionLog = null;
		boolean ok = true;
		
		if (request.getSession(false) == null || request.getSession().getAttribute(SessionLog.class.getName()) == null) {
			if (hasToManySession(request)) {
				ok = false;
				handleTooManySession(request);
			} else {
				// create new session if needed
				HttpSession session = request.getSession();
				sessionLog = createNewSessionLog(request);
				session.setAttribute(SessionLog.class.getName(), sessionLog);
			}
		} else {
			sessionLog = (SessionLog) request.getSession().getAttribute(SessionLog.class.getName());
			if (!sessionLog.getIp().equalsIgnoreCase(RemoteInfoUtil.getClientIpAddr(request))) {
				request.getSession().invalidate();
				response.sendError(HttpServletResponse.SC_CONFLICT);
			} else {
				ok = false;
				populateSessionLog(request, sessionLog);
			}
		}
		
		if (ok) {
			if (sessionLog != null && isLoginAccess(request) && isLoginThresholdEnable(request, sessionLog)) {
				handleTooManyLoginAttempt(request);
			} else {
				filterChain.doFilter(request, response);
			}
			timer = System.currentTimeMillis() - timer;
			if (sessionLog != null) {
				sessionLog.addServerTime(timer);
			}
		}
		
		
	}
	
	private boolean isLoginAccess(HttpServletRequest request) {
		return request.getRequestURI().contains("/login") || request.getRequestURI().contains("/j_spring_security_check");
	}
	
	private boolean hasToManySession(HttpServletRequest request) {
		
		String ip = RemoteInfoUtil.getClientIpAddr(request);
		List<Date> ipSessionDates = getSessionIpDates(ip);
		
		if (ipSessionDates != null && !ipSessionDates.isEmpty()) {
			long currentTime = System.currentTimeMillis();
			long past10s = currentTime - (10 * 100);
			long past1min = currentTime - (60 * 100);
			long past30min = currentTime - (30 * 60 * 100);
			int count10s = 0;
			int count1min = 0;
			int count30min = 0;
			for (Date sessionDate : ipSessionDates) {
				long tsession = sessionDate.getTime();
				if (tsession >= past10s) {
					count10s++;
				} else if (tsession >= past1min) {
					count1min++;
				} else if (tsession >= past30min) {
					count30min++;
				}
			}
			count1min += count10s;
			count30min += count1min;
			return (count10s >= SESSION_IP_THRESHOLD_10S) || (count1min >= SESSION_IP_THRESHOLD_1MIN) || (count30min >= SESSION_IP_THRESHOLD_30MIN);
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private List<Date> getSessionIpDates(String ip) {
		
		ServletContext sc = getServletContext();
		Map<String, List<Date>> mapIpListDates = null;
		synchronized (sc) {
			mapIpListDates = (Map<String, List<Date>>) sc.getAttribute("SessionIpDates");
			if (mapIpListDates == null) {
				mapIpListDates = new HashMap<String, List<Date>>();
				sc.setAttribute("SessionIpDates", mapIpListDates);
			}
		}
		
		List<Date> ipSessionDates = null;
		synchronized (mapIpListDates) {
			if (!mapIpListDates.isEmpty()) {
				clearOldEntries(mapIpListDates);
			}
			if (mapIpListDates.containsKey(ip)) {
				ipSessionDates = mapIpListDates.get(ip);
			}
			if (ipSessionDates == null) {
				ipSessionDates = new ArrayList<Date>();
				mapIpListDates.put(ip, ipSessionDates);
			}
			ipSessionDates.add(new Date());
		}
		
		return ipSessionDates;
	}
	
	private void clearOldEntries(Map<String, List<Date>> mapIpListDates) {
		long past30min = System.currentTimeMillis() - (30 * 60 * 100);
		Iterator<Entry<String, List<Date>>> it = mapIpListDates.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, List<Date>> entry = it.next();
			List<Date> listDate = entry.getValue();
			Iterator<Date> lit = listDate.iterator();
			while (lit.hasNext()) {
				Date d = lit.next();
				if (d.getTime() <= past30min) {
					lit.remove();
				}
			}
			if (listDate.isEmpty()) {
				it.remove();
			}
		}
	}
	
	private boolean isLoginThresholdEnable(HttpServletRequest request, SessionLog sessionLog) {
		boolean res = false;
		if (sessionLog.getNbSuccessiveLoginAttempts() >= LOGIN_THRESHOLD) {
			if (sessionLog.getLastLoginAttempts().getTime() + LOGIN_THRESHOLD_TIMEOUT > System.currentTimeMillis()) {
				res = true;
			}
		}
		return res;
	}
	
	private void populateSessionLog(HttpServletRequest request, SessionLog sessionLog) {
		if (isRessource(request)) {
			sessionLog.setNbRequestRessources(sessionLog.getNbRequestRessources() + 1);
		} else {
			sessionLog.setNbRequestPages(sessionLog.getNbRequestPages() + 1);
			if (request.getRequestURI().contains(GenericController.LOGIN_PROCESS_URL)) {
				if (sessionLog.getLastLoginAttempts() != null) {
					Date lastTry = sessionLog.getLastLoginAttempts();
					Date timeOut = new Date(lastTry.getTime() + LOGIN_THRESHOLD_TIMEOUT);
					if (timeOut.before(new Date())) {
						sessionLog.setNbSuccessiveLoginAttempts(1);
					} else {
						sessionLog.setNbSuccessiveLoginAttempts(sessionLog.getNbSuccessiveLoginAttempts() + 1);
					}
				} else {
					sessionLog.setNbSuccessiveLoginAttempts(1);
				}
				sessionLog.setLastLoginAttempts(new Date());
			}
		}
	}
	
	private SessionLog createNewSessionLog(HttpServletRequest request) {
		SessionLog sessionLog = new SessionLog(request);
		populateSessionLog(request, sessionLog);
		return sessionLog;
	}
	
	private boolean isRessource(HttpServletRequest request) {
		String reqUri = request.getRequestURI().toLowerCase();
		for (String ressourceExt : RESSOURCE_EXTS) {
			if (reqUri.contains(ressourceExt)) { return true; }
		}
		return false;
	}
	
	private void handleTooManyLoginAttempt(HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
	
	private void handleTooManySession(HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
}
