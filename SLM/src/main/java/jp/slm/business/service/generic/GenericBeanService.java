package jp.slm.business.service.generic;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import jp.slm.business.bean.generic.GenericBean;
import jp.slm.business.dao.generic.GenericBeanDao;


/**
 * The Interface GenericBeanService.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@Transactional
public interface GenericBeanService<T extends GenericBean, PK extends Serializable> extends GenericService<T, PK> {

    /**
     * Gets the dao.
     * 
     * @return the dao
     */
    @Override
    GenericBeanDao<T, PK> getDao();
}
