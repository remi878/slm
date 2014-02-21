package jp.slm.business.service.generic;

import jp.slm.business.bean.generic.GenericPkBean;
import jp.slm.business.dao.generic.GenericLongIdBeanDao;


/**
 * The Interface GenericLongIdBeanService.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 */
public interface GenericLongIdBeanService<T extends GenericPkBean<Long>> extends GenericPkBeanService<T, Long> {

    /**
     * Gets the dao.
     * 
     * @return the dao
     */
    @Override
    GenericLongIdBeanDao<T> getDao();
}
