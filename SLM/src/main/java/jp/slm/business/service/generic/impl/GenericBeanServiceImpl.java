package jp.slm.business.service.generic.impl;

import java.io.Serializable;

import jp.slm.business.bean.generic.GenericBean;
import jp.slm.business.dao.generic.GenericBeanDao;
import jp.slm.business.service.generic.GenericBeanService;

import org.springframework.stereotype.Repository;

/**
 * The Class GenericBeanServiceImpl.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@SuppressWarnings("serial")
@Repository
public abstract class GenericBeanServiceImpl<T extends GenericBean, PK extends Serializable> extends GenericServiceImpl<T, PK> implements
    GenericBeanService<T, PK>
{

    @Override
    public abstract GenericBeanDao<T, PK> getDao();

}
