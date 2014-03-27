package jp.slm.business.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import jp.slm.business.bean.generic.GenericLongIdBean;
import jp.slm.web.util.RemoteInfoUtil;

@SuppressWarnings("serial")
@Entity
@Table(name = "session_log")
public class SessionLog extends GenericLongIdBean {
	
	private String ip = null;
	
	private Date start = new Date();
	
	private Date end = null;
	
	private String referer = null;
	
	private String header = null;
	
	private String requestedHost = null;
	
	private int nbSuccessiveLoginAttempts = 0;
	
	private Date lastLoginAttempts = null;
	
	private String userIds = null;
	
	private int nbRequestPages = 0;

	private int nbRequestRessources = 0;
	
	private long serverTime = 0;
	
	public SessionLog() {}
	
	public SessionLog(HttpServletRequest request) {
		this.ip = RemoteInfoUtil.getClientIpAddr(request);
		if (RemoteInfoUtil.hasHeaders(request)) {
			this.header = RemoteInfoUtil.getHeaders(request);
			if (RemoteInfoUtil.hasHost(request)) {
				this.requestedHost = RemoteInfoUtil.getHost(request);
			}
			if (RemoteInfoUtil.hasReferer(request)) {
				this.requestedHost = RemoteInfoUtil.getReferer(request);
			}
		}
	}

	@Column(name = "ip", nullable = false)
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10, nullable = false)
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 10, nullable = false)
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}

	@Column(name = "referer", nullable = true)
	public String getReferer() {
		return referer;
	}
	
	public void setReferer(String referer) {
		this.referer = referer;
	}

	@Column(name = "header", nullable = true)
	public String getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}

	@Column(name = "requested_host", nullable = true)
	public String getRequestedHost() {
		return requestedHost;
	}
	
	public void setRequestedHost(String requestedHost) {
		this.requestedHost = requestedHost;
	}

	@Column(name = "nb_successive_login_attempts", nullable = true)
	public int getNbSuccessiveLoginAttempts() {
		return nbSuccessiveLoginAttempts;
	}
	
	public void setNbSuccessiveLoginAttempts(int nbSuccessiveLoginAttempts) {
		this.nbSuccessiveLoginAttempts = nbSuccessiveLoginAttempts;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_login_attempt", length = 10)
	public Date getLastLoginAttempts() {
		return lastLoginAttempts;
	}
	
	public void setLastLoginAttempts(Date lastLoginAttempts) {
		this.lastLoginAttempts = lastLoginAttempts;
	}

	@Column(name = "user_ids", nullable = true)
	public String getUserIds() {
		return userIds;
	}
	
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	@Column(name = "nb_request_pages", nullable = true)
	public int getNbRequestPages() {
		return nbRequestPages;
	}
	
	public void setNbRequestPages(int nbRequestPages) {
		this.nbRequestPages = nbRequestPages;
	}

	@Column(name = "nb_request_ressources", nullable = true)
	public int getNbRequestRessources() {
		return nbRequestRessources;
	}
	
	public void setNbRequestRessources(int nbRequestRessources) {
		this.nbRequestRessources = nbRequestRessources;
	}

	@Column(name = "server_time", nullable = true)
	public long getServerTime() {
		return serverTime;
	}

	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}
	
	@Transient
	public void addUserId(String userId) {
		if (this.userIds == null) {
			this.userIds = userId;
		} else if (!this.userIds.contains(userId)) {
			this.userIds += ";" + userId;
		}
	}

	@Transient
	public void addServerTime(long timer) {
		this.serverTime += timer;
	}
}
