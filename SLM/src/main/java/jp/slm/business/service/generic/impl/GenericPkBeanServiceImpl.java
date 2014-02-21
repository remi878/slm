package jp.slm.business.service.generic.impl;

import java.io.Serializable;

import jp.slm.business.bean.generic.GenericPkBean;
import jp.slm.business.dao.generic.GenericPkBeanDao;
import jp.slm.business.service.generic.GenericPkBeanService;

import org.springframework.stereotype.Repository;

/**
 * The Class GenericPkBeanServiceImpl.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@SuppressWarnings("serial")
@Repository
public abstract class GenericPkBeanServiceImpl<T extends GenericPkBean<PK>, PK extends Serializable> extends GenericBeanServiceImpl<T, PK>
    implements GenericPkBeanService<T, PK>
{

    @Override
    public abstract GenericPkBeanDao<T, PK> getDao();

}
