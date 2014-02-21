package jp.slm.business.dao.generic;

import java.io.Serializable;

import jp.slm.business.bean.generic.GenericPkBean;


/**
 * The Interface GenericPkBeanDao.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
public interface GenericPkBeanDao<T extends GenericPkBean<PK>, PK extends Serializable> extends GenericBeanDao<T, PK> {

}
