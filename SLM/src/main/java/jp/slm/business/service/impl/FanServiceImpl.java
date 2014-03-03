package jp.slm.business.service.impl;

import jp.slm.business.bean.Fan;
import jp.slm.business.dao.FanDao;
import jp.slm.business.service.FanService;
import jp.slm.business.service.generic.impl.GenericLongIdBeanServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The FanServiceImpl.
 */
@Service
@SuppressWarnings("serial")
public class FanServiceImpl extends GenericLongIdBeanServiceImpl<Fan> implements FanService
{
    @Autowired
    private FanDao dao;

    @Override
    public FanDao getDao()
    {
        return dao;
    }
}
