package jp.slm.business.dao.generic.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import jp.slm.business.dao.generic.GenericDao;

import org.hibernate.Cache;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * The Class GenericDaoImpl.
 * 
 * @param <T>
 *            the generic type for the mapped object with hibernate
 * @param <PK>
 *            the generic type for the primary key of the object
 */
@SuppressWarnings({ "unchecked", "serial" })
@Repository
public abstract class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK>, Serializable {
	
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	/** The type. */
	protected Class<T> type;
	
	/**
	 * The Constructor.
	 */
	protected GenericDaoImpl() {}
	
	/**
	 * The Constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	protected GenericDaoImpl(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	/**
	 * Creates the criteria.
	 * 
	 * @return the criteria
	 */
	protected Criteria createCriteria() {
		return currentSession().createCriteria(type);
	}
	
	/**
	 * Creates the criteria.
	 * 
	 * @param newType
	 *            the new type
	 * @return the criteria
	 */
	protected Criteria createCriteria(Class<?> newType) {
		return currentSession().createCriteria(newType);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#create(java.lang.Object)
	 */
	public PK create(T o) {
		return (PK) getHibernateTemplate().save(o);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#findById(java.io.Serializable)
	 */
	public T findById(PK id) {
		return (T) getHibernateTemplate().get(type, id);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#existById(java.io.Serializable)
	 */
	public boolean existById(PK id) {
		return existByCriterion(Restrictions.idEq(id));
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#listAll()
	 */
	public List<T> listAll() {
		return this.createCriteria().list();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#listAllPk()
	 */
	public List<PK> listAllPk() {
		Criteria c = this.createCriteria();
		c.setProjection(Projections.id());
		return c.list();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#countAll()
	 */
	public Long countAll() {
		return (Long) this.createCriteria().setProjection(Projections.rowCount()).setMaxResults(1).uniqueResult();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#listByProperty(java.lang.String, java.lang.Object)
	 */
	public List<T> listByProperty(String propertyName, Object value) {
		Criteria c = this.createCriteria();
		c.add(Restrictions.eq(propertyName, value));
		return c.list();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#listByCriterion(org.hibernate.criterion.Criterion)
	 */
	public List<T> listByCriterion(Criterion criterion) {
		Criteria c = this.createCriteria();
		c.add(criterion);
		return c.list();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#listByCriterionAndOrder(org.hibernate.criterion.Criterion, org.hibernate.criterion.Order)
	 */
	public List<T> listByCriterionAndOrder(Criterion criterion, Order order) {
		Criteria c = this.createCriteria();
		c.add(criterion);
		c.addOrder(order);
		return c.list();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#listAllWithProjection(java.lang.String)
	 */
	public List<?> listAllWithProjection(String propertyName) {
		Criteria c = this.createCriteria();
		c.setProjection(Projections.property(propertyName));
		return c.list();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public T findByProperty(String propertyName, Object value) {
		Criteria c = this.createCriteria();
		c.add(Restrictions.eq(propertyName, value));
		c.setMaxResults(1);
		return (T) c.uniqueResult();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#countByProperty(java.lang.String, java.lang.Object)
	 */
	public Long countByProperty(String propertyName, Object value) {
		Criteria c = this.createCriteria();
		c.add(Restrictions.eq(propertyName, value));
		c.setProjection(Projections.rowCount());
		c.setMaxResults(1);
		return (Long) c.uniqueResult();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#existByProperty(java.lang.String, java.lang.Object)
	 */
	public boolean existByProperty(String propertyName, Object value) {
		Long count = countByProperty(propertyName, value);
		return count != null ? count.longValue() > 0 : false;
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#findByCriterion(org.hibernate.criterion.Criterion)
	 */
	public T findByCriterion(Criterion criterion) {
		Criteria c = this.createCriteria();
		c.add(criterion);
		c.setMaxResults(1);
		return (T) c.uniqueResult();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#countByCriterion(org.hibernate.criterion.Criterion)
	 */
	public Long countByCriterion(Criterion criterion) {
		Criteria c = this.createCriteria();
		c.add(criterion);
		c.setProjection(Projections.rowCount());
		c.setMaxResults(1);
		return (Long) c.uniqueResult();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#existByCriterion(org.hibernate.criterion.Criterion)
	 */
	public boolean existByCriterion(Criterion criterion) {
		Long count = countByCriterion(criterion);
		return count != null ? count.intValue() > 0 : false;
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#update(java.lang.Object)
	 */
	public void update(T o) {
		getHibernateTemplate().saveOrUpdate(o);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#updateAll(java.util.List)
	 */
	public void updateAll(Collection<? extends T> all) {
		for (T element : all) {
			getHibernateTemplate().saveOrUpdate(element);
		}
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#merge(java.lang.Object)
	 */
	public void merge(T o) {
		getHibernateTemplate().merge(o);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#delete(java.lang.Object)
	 */
	public void delete(T o) {
		getHibernateTemplate().delete(o);
	}
	
	/**
	 * Delete all.
	 * 
	 * @param all
	 *            the all
	 */
	public void deleteAll(Collection<? extends T> all) {
		getHibernateTemplate().deleteAll(all);
	}
	
	/**
	 * Prints the cache stats.
	 * 
	 * @param strClassName
	 *            the str class name
	 */
	protected void printCacheStats(String strClassName) {
		if (logger.isInfoEnabled()) {
			
			Statistics stats = getSessionFactory().getStatistics();
			EntityStatistics entityStats = stats.getEntityStatistics(strClassName);
			
			double queryCacheHitCount = stats.getQueryCacheHitCount();
			double queryCacheHitRatio = queryCacheHitCount / (queryCacheHitCount + stats.getQueryCacheMissCount());
			long changes = entityStats.getInsertCount() + entityStats.getUpdateCount() + entityStats.getDeleteCount();
			
			logger.info(strClassName + " : query Hit ratio " + queryCacheHitRatio + "( changed " + changes + " times )");
		}
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#evict(java.lang.Object)
	 */
	public void evict(T o) {
		getHibernateTemplate().evict(o);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#evictObject(java.lang.Object)
	 */
	public void evictObject(Object o) {
		getHibernateTemplate().evict(o);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#refresh(java.lang.Object)
	 */
	public void refresh(T o) {
		getHibernateTemplate().refresh(o);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#refreshObject(java.lang.Object)
	 */
	public void refreshObject(Object o) {
		getHibernateTemplate().refresh(o);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#clearSession()
	 */
	public void clearSession() {
		currentSession().clear();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#clearCache()
	 */
	public void clearCache() {
		Cache cache = getCache();
		if (cache != null) {
			cache.evictAllRegions();
		}
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#clearClassCache()
	 */
	public void clearClassCache() {
		Cache cache = getCache();
		if(cache!=null){
			cache.evictEntityRegion(this.type);
		}
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#clearInstanceCache()
	 */
	public void clearInstanceCache(PK id) {
		Cache cache = getCache();
		if (cache != null) {
			cache.evictEntity(this.type, id);
		}
	}
	
	private Cache getCache() {
		return getSessionFactory().getCache();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#deleteByPk(java.lang.Object)
	 */
	public int deleteByPk(PK pk) {
		return deleteByProperty(getPkName(), pk);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#deleteAllByPk(java.util.Collection)
	 */
	public int deleteAllByPk(Collection<? extends PK> pks) {
		return deleteByProperty(getPkName(), pks);
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#deleteByProperty(java.lang.String, java.lang.Object)
	 */
	public int deleteByProperty(String propertyName, Object value) {
		Query query = createHQLQuery("delete " + type.getName() + " where " + propertyName + " = (:value)");
		query.setParameter("values", value);
		return query.executeUpdate();
	}
	
	/**
	 * (methode de remplacement) {@inheritDoc}
	 * 
	 * @see jp.slm.business.dao.generic.GenericDao#deleteByProperty(java.lang.String, java.util.Collection)
	 */
	public int deleteByProperty(String propertyName, Collection<? extends Object> values) {
		Query query = createHQLQuery("delete " + type.getName() + " where " + propertyName + " in (:values)");
		query.setParameterList("values", values);
		return query.executeUpdate();
	}
	
	protected Query createHQLQuery(String query) {
		return currentSession().createQuery(query);
	}
	
	protected Query createQuery(String query) {
		return createHQLQuery(query);
	}
	
	abstract protected String getPkName();
}
