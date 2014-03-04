package jp.slm.business.dao.generic.impl;

import java.io.Serializable;

import jp.slm.business.bean.generic.GenericBean;
import jp.slm.business.dao.generic.GenericBeanDao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


/**
 * The Class GenericBeanDaoImpl.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@SuppressWarnings("serial")
@Repository
public abstract class GenericBeanDaoImpl<T extends GenericBean, PK extends Serializable> extends GenericDaoImpl<T, PK> implements GenericBeanDao<T,PK> {

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     */
	protected GenericBeanDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     * @param type the type
     */
	protected GenericBeanDaoImpl(SessionFactory sessionFactory, Class<T> type) {
		super(sessionFactory);
		this.type = type;
	}

}
