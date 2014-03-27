package jp.slm.web.util;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unchecked")
public class RemoteInfoUtil {
	
	public static final Logger LOG = LoggerFactory.getLogger(RemoteInfoUtil.class);
	
	private static final String UNKNOWN = "unknown";
	
	private static final String VALUES_SEP = ", ";
	
	private static final String NAME_VALUE_SEP = ": ";
	
	private static final String NAMES_SEP = " ; ";
	
	private static final String REFERER = "Referer";
	
	private static final String HOST = "Referer";
	
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// test
		String ipRealX = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ipRealX) || UNKNOWN.equalsIgnoreCase(ipRealX)) {
			ip += " [X-Real-IP:" + ipRealX + "]";
		}
		return ip;
	}
	
	public static String getHeaders(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		if (hasHeaders(request)) {
			for (Object objName : Collections.list(request.getHeaderNames())) {
				String name = objName.toString();
				if (hasHeaders(request, name)) {
					if (sb.length() > 0) {
						sb.append(NAMES_SEP);
					}
					sb.append(name).append(NAME_VALUE_SEP).append(getHeaderValue(request, name));
				}
			}
		}
		return sb.toString();
	}
	
	public static String getHeaderValue(HttpServletRequest request, String name) {
		StringBuilder sb = new StringBuilder();
		if (hasHeaders(request, name)) {
			for (Object objHeader : Collections.list(request.getHeaders(name))) {
				if (sb.length() > 0) {
					sb.append(VALUES_SEP);
				}
				sb.append(objHeader);
			}
		}
		return sb.toString();
	}
	
	public static String getReferer(HttpServletRequest request) {
		return getHeaderValue(request, REFERER);
	}
	
	public static String getHost(HttpServletRequest request) {
		return getHeaderValue(request, HOST);
	}
	
	public static boolean hasReferer(HttpServletRequest request) {
		return hasHeaders(request, REFERER);
	}
	
	public static boolean hasHost(HttpServletRequest request) {
		return hasHeaders(request, HOST);
	}
	
	public static boolean hasHeaders(HttpServletRequest request) {
		return request != null && request.getHeaderNames() != null && request.getHeaderNames().hasMoreElements();
	}
	
	public static boolean hasHeaders(HttpServletRequest request, String name) {
		return request != null && request.getHeaders(name) != null && request.getHeaders(name).hasMoreElements();
	}
}
