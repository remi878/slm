package jp.slm.business.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.slm.business.bean.Avatar;
import jp.slm.business.dao.AvatarDao;
import jp.slm.business.dao.generic.impl.GenericLongIdBeanDaoImpl;

/**
 * The Interface AvatarDao.
 */
@Repository
@SuppressWarnings("serial")
public class AvatarDaoImpl extends GenericLongIdBeanDaoImpl<Avatar> implements AvatarDao
{
    @Autowired
    public AvatarDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, Avatar.class);
    }
}
