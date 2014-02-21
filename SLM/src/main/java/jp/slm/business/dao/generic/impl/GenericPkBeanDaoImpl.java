package jp.slm.business.dao.generic.impl;

import java.io.Serializable;

import jp.slm.business.bean.generic.GenericPkBean;
import jp.slm.business.dao.generic.GenericPkBeanDao;

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
public class GenericPkBeanDaoImpl<T extends GenericPkBean<PK>, PK extends Serializable> extends GenericBeanDaoImpl<T, PK> implements GenericPkBeanDao<T,PK> {

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     */
	protected GenericPkBeanDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     * @param type the type
     */
	protected GenericPkBeanDaoImpl(SessionFactory sessionFactory, Class<T> type) {
		super(sessionFactory);
		this.type = type;
	}

}