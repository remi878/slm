package jp.slm.business.service.generic;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import jp.slm.business.bean.generic.GenericPkBean;
import jp.slm.business.dao.generic.GenericPkBeanDao;


/**
 * The Interface GenericPkBeanService.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@Transactional
public interface GenericPkBeanService<T extends GenericPkBean<PK>, PK extends Serializable> extends GenericBeanService<T, PK> {


    /**
     * Gets the dao.
     * 
     * @return the dao
     */
    @Override
    abstract GenericPkBeanDao<T, PK> getDao();
}
