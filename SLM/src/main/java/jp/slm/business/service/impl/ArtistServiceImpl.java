package jp.slm.business.service.impl;

import jp.slm.business.bean.Artist;
import jp.slm.business.dao.ArtistDao;
import jp.slm.business.service.generic.impl.GenericLongIdBeanServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ArtistServiceImpl.
 */
@Service
@SuppressWarnings("serial")
public class ArtistServiceImpl extends GenericLongIdBeanServiceImpl<Artist> implements ArtistDao
{
    @Autowired
    private ArtistDao dao;

    @Override
    public ArtistDao getDao()
    {
        return dao;
    }
}
