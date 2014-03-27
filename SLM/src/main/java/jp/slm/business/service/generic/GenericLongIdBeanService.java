package jp.slm.business.service.generic;

import org.springframework.transaction.annotation.Transactional;

import jp.slm.business.bean.generic.GenericPkBean;
import jp.slm.business.dao.generic.GenericLongIdBeanDao;


/**
 * The Interface GenericLongIdBeanService.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 */
@Transactional
public interface GenericLongIdBeanService<T extends GenericPkBean<Long>> extends GenericPkBeanService<T, Long> {

    /**
     * Gets the dao.
     * 
     * @return the dao
     */
    @Override
    abstract GenericLongIdBeanDao<T> getDao();
}
