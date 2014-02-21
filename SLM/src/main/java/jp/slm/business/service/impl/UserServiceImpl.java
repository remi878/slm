package jp.slm.business.service.impl;

import jp.slm.business.bean.User;
import jp.slm.business.dao.UserDao;
import jp.slm.business.service.generic.impl.GenericLongIdBeanServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The UserServiceImpl.
 */
@Service
@SuppressWarnings("serial")
public class UserServiceImpl extends GenericLongIdBeanServiceImpl<User> implements UserDao
{
    @Autowired
    private UserDao dao;

    @Override
    public UserDao getDao()
    {
        return dao;
    }
}
