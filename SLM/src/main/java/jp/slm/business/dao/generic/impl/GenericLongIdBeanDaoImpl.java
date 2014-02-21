package jp.slm.business.dao.generic.impl;

import jp.slm.business.bean.generic.GenericLongIdBean;
import jp.slm.business.dao.generic.GenericLongIdBeanDao;

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
public class GenericLongIdBeanDaoImpl<T extends GenericLongIdBean> extends GenericPkBeanDaoImpl<T, Long> implements GenericLongIdBeanDao<T> {

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     */
	protected GenericLongIdBeanDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
     * The Constructor.
     * 
     * @param sessionFactory the session factory
     * @param type the type
     */
	protected GenericLongIdBeanDaoImpl(SessionFactory sessionFactory, Class<T> type) {
		super(sessionFactory);
		this.type = type;
	}

}
