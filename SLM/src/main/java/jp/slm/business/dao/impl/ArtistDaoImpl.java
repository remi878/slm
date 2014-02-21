package jp.slm.business.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.slm.business.bean.Artist;
import jp.slm.business.dao.ArtistDao;
import jp.slm.business.dao.generic.impl.GenericLongIdBeanDaoImpl;

/**
 * The Interface ArtistDao.
 */
@Repository
@SuppressWarnings("serial")
public class ArtistDaoImpl extends GenericLongIdBeanDaoImpl<Artist> implements ArtistDao
{
    @Autowired
    public ArtistDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, Artist.class);
    }
}
