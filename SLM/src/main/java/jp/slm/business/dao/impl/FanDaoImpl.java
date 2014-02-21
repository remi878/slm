package jp.slm.business.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.slm.business.bean.Fan;
import jp.slm.business.dao.FanDao;
import jp.slm.business.dao.generic.impl.GenericLongIdBeanDaoImpl;

/**
 * The Interface FanDao.
 */
@Repository
@SuppressWarnings("serial")
public class FanDaoImpl extends GenericLongIdBeanDaoImpl<Fan> implements FanDao
{
    @Autowired
    public FanDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, Fan.class);
    }
}
