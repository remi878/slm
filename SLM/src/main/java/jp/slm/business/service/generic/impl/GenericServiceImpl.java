package jp.slm.business.service.generic.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import jp.slm.business.dao.generic.GenericDao;
import jp.slm.business.service.generic.GenericService;

@SuppressWarnings("serial")
public abstract class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T,PK> {

    abstract public GenericDao<T, PK> getDao();

    @Override
    public Integer countAll()
    {
        return getDao().countAll();
    }

    @Override
    public Long countByCriterion(Criterion criterion)
    {
        return getDao().countByCriterion(criterion);
    }

    @Override
    public Integer countByProperty(String propertyName, Object value)
    {
        return getDao().countByProperty(propertyName, value);
    }

    @Override
    public PK create(T newInstance)
    {
        return getDao().create(newInstance);
    }

    @Override
    public void delete(T persistentObject)
    {
        getDao().delete(persistentObject);
    }

    @Override
    public void deleteAll(Collection<T> collection)
    {
        getDao().deleteAll(collection);
    }

    @Override
    public void evict(T o)
    {
        getDao().evict(o);
    }

    @Override
    public void evictObject(Object o)
    {
        getDao().evictObject(o);
    }

    @Override
    public boolean existByCriterion(Criterion criterion)
    {
        return getDao().existByCriterion(criterion);
    }

    @Override
    public boolean existById(PK id)
    {
        return getDao().existById(id);
    }

    @Override
    public boolean existByProperty(String propertyName, Object value)
    {
        return getDao().existByProperty(propertyName, value);
    }

    @Override
    public T findByCriterion(Criterion criterion)
    {
        return getDao().findByCriterion(criterion);
    }

    @Override
    public T findById(PK id)
    {
        return getDao().findById(id);
    }

    @Override
    public T findByProperty(String propertyName, Object value)
    {
        return getDao().findByProperty(propertyName, value);
    }

    @Override
    public List<T> listAll()
    {
        return getDao().listAll();
    }

    @Override
    public List<PK> listAllPk()
    {
        return getDao().listAllPk();
    }

    @Override
    public List<?> listAllWithProjection(String propertyName)
    {
        return getDao().listAllWithProjection(propertyName);
    }

    @Override
    public List<T> listByCriterion(Criterion criterion)
    {
        return getDao().listByCriterion(criterion);
    }

    @Override
    public List<T> listByCriterionAndOrder(Criterion criterion, Order order)
    {
        return getDao().listByCriterionAndOrder(criterion, order);
    }

    @Override
    public List<T> listByProperty(String propertyName, Object value)
    {
        return getDao().listByProperty(propertyName, value);
    }

    @Override
    public void merge(T transientObject)
    {
        getDao().merge(transientObject);
    }

    @Override
    public void refresh(T o)
    {
        getDao().refresh(o);
    }

    @Override
    public void refreshObject(Object o)
    {
        getDao().refreshObject(o);
    }

    @Override
    public void update(T transientObject)
    {
        getDao().update(transientObject);
    }

    @Override
    public void updateAll(Collection<T> collection)
    {
        getDao().updateAll(collection);
    }
}
