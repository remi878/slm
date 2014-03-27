package jp.slm.business.dao.impl;

import jp.slm.business.bean.SessionLog;
import jp.slm.business.dao.SessionLogDao;
import jp.slm.business.dao.generic.impl.GenericLongIdBeanDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The Interface SessionLogDao.
 */
@Repository
@SuppressWarnings("serial")
public class SessionLogDaoImpl extends GenericLongIdBeanDaoImpl<SessionLog> implements SessionLogDao
{
	
    @Autowired
    public SessionLogDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, SessionLog.class);
    }

}
