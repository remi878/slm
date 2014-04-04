package jp.slm.business.service.impl;

import jp.slm.business.bean.Avatar;
import jp.slm.business.dao.AvatarDao;
import jp.slm.business.service.AvatarService;
import jp.slm.business.service.generic.impl.GenericLongIdBeanServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The AvatarServiceImpl.
 */
@Service
@SuppressWarnings("serial")
public class AvatarServiceImpl extends GenericLongIdBeanServiceImpl<Avatar> implements AvatarService
{
    @Autowired
    private AvatarDao dao;

    @Override
    public AvatarDao getDao()
    {
        return dao;
    }
}
