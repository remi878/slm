package jp.slm.business.dao.generic.impl;

import jp.slm.business.bean.generic.GenericIntegerIdBean;
import jp.slm.business.dao.generic.GenericIntegerIdBeanDao;

import org.hibernate.SessionFactory;


/**
 * The Class GenericBeanDaoImpl.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
@SuppressWarnings("serial")
public abstract class GenericIntegerIdBeanDaoImpl<T extends GenericIntegerIdBean> extends GenericPkBeanDaoImpl<T, Integer> implements GenericIntegerIdBeanDao<T> {

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     */
	protected GenericIntegerIdBeanDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     * @param type the type
     */
	protected GenericIntegerIdBeanDaoImpl(SessionFactory sessionFactory, Class<T> type) {
		super(sessionFactory);
		this.type = type;
	}

}
