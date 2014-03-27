package jp.slm.business.service.impl;

import jp.slm.business.bean.SessionLog;
import jp.slm.business.dao.SessionLogDao;
import jp.slm.business.service.SessionLogService;
import jp.slm.business.service.generic.impl.GenericLongIdBeanServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service
@Transactional
public class SessionLogServiceImpl extends GenericLongIdBeanServiceImpl<SessionLog> implements SessionLogService {
	
	@Autowired
	private SessionLogDao dao;
	
	@Override
	public SessionLogDao getDao() {
		return dao;
	}

	@Override
	public boolean hasTooManySession(String ip) {
		boolean res = false;
		return res;
	}
}
