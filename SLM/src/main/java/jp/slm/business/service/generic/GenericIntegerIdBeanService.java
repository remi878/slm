package jp.slm.business.service.generic;

import jp.slm.business.bean.generic.GenericPkBean;
import jp.slm.business.dao.generic.GenericIntegerIdBeanDao;


/**
 * The Interface GenericIntegerIdBeanService.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 */
public interface GenericIntegerIdBeanService<T extends GenericPkBean<Integer>> extends GenericPkBeanService<T, Integer> {

    /**
     * Gets the dao.
     * 
     * @return the dao
     */
    @Override
    GenericIntegerIdBeanDao<T> getDao();
}
