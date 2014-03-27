package jp.slm.business.service;

import org.springframework.transaction.annotation.Transactional;

import jp.slm.business.bean.SessionLog;
import jp.slm.business.service.generic.GenericLongIdBeanService;

/**
 * The Interface SessionLogService.
 * 
 */
@Transactional
public interface SessionLogService extends GenericLongIdBeanService<SessionLog> {
	
	boolean hasTooManySession(String ip);
	
}
