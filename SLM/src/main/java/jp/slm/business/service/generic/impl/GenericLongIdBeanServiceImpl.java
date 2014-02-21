package jp.slm.business.service.generic.impl;

import jp.slm.business.bean.generic.GenericLongIdBean;
import jp.slm.business.dao.generic.GenericLongIdBeanDao;
import jp.slm.business.service.generic.GenericLongIdBeanService;

import org.springframework.stereotype.Repository;

/**
 * The Class GenericLongIdBeanServiceImpl.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@SuppressWarnings("serial")
@Repository
public abstract class GenericLongIdBeanServiceImpl<T extends GenericLongIdBean> extends GenericPkBeanServiceImpl<T, Long>
    implements GenericLongIdBeanService<T>
{

    @Override
    public abstract GenericLongIdBeanDao<T> getDao();

}
