package jp.slm.business.service.generic.impl;

import jp.slm.business.bean.generic.GenericIntegerIdBean;
import jp.slm.business.dao.generic.GenericIntegerIdBeanDao;
import jp.slm.business.service.generic.GenericIntegerIdBeanService;

import org.springframework.stereotype.Repository;

/**
 * The Class GenericIntegerIdBeanServiceImpl.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@SuppressWarnings("serial")
@Repository
public abstract class GenericIntegerIdBeanServiceImpl<T extends GenericIntegerIdBean> extends GenericPkBeanServiceImpl<T, Integer>
    implements GenericIntegerIdBeanService<T>
{

    @Override
    public abstract GenericIntegerIdBeanDao<T> getDao();

}
