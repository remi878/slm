package jp.slm.business.dao.generic;

import java.io.Serializable;

import jp.slm.business.bean.generic.GenericBean;


/**
 * The Interface GenericBeanDao.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
public interface GenericBeanDao<T extends GenericBean, PK extends Serializable> extends GenericDao<T, PK> {

}
