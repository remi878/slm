package jp.slm.business.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.slm.business.bean.User;
import jp.slm.business.dao.UserDao;
import jp.slm.business.dao.generic.impl.GenericLongIdBeanDaoImpl;

/**
 * The Interface UserDao.
 */
@Repository
@SuppressWarnings("serial")
public class UserDaoImpl extends GenericLongIdBeanDaoImpl<User> implements UserDao
{
    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, User.class);
    }
}
