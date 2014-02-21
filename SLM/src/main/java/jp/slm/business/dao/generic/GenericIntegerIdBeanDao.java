package jp.slm.business.dao.generic;

import jp.slm.business.bean.generic.GenericPkBean;


/**
 * The Interface GenericIntegerIdBeanDao.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 */
public interface GenericIntegerIdBeanDao<T extends GenericPkBean<Integer>> extends GenericPkBeanDao<T, Integer> {

}
