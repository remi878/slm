package jp.slm.business.dao.generic;

import jp.slm.business.bean.generic.GenericPkBean;


/**
 * The Interface GenericLongIdBeanDao.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 */
public interface GenericLongIdBeanDao<T extends GenericPkBean<Long>> extends GenericPkBeanDao<T, Long> {

}
