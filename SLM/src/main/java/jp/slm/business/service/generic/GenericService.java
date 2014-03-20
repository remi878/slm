package jp.slm.business.service.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import jp.slm.business.dao.generic.GenericDao;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * The Interface GenericService.
 * 
 * @param <T> the generic type for the mapped object with hibernate
 * @param <PK> the generic type for the primary key of the object
 */
public interface GenericService<T, PK extends Serializable> extends Serializable {
    

    /**
     * Gets the dao.
     * 
     * @return the dao
     */
    GenericDao<T, PK> getDao();
    
    /**
     * Count all.
     * 
     * @return the Long
     */
    Long countAll();

    /**
     * Count by criterion.
     * 
     * @param criterion the criterion
     * @return the long
     */
    Long countByCriterion(Criterion criterion);
    
    /**
     * Count by property.
     * 
     * @param propertyName the property name
     * @param value the value
     * @return the Long
     */
    Long countByProperty(String propertyName, Object value);

    /**
     * Creates the.
     * 
     * @param newInstance the new instance
     * @return the PK
     */
    PK create(T newInstance);
    
    /**
     * Delete.
     * 
     * @param persistentObject the persistent object
     */
    void delete(T persistentObject);

    /**
     * Delete all.
     * 
     * @param collection the collection
     */
    void deleteAll(Collection<? extends T> collection);
    
    /**
     * Delete by PK.
     * 
     * @param pk PK to delete
     * @return the int number of delete
     */
    int deleteByPk(PK pk);
    
    /**
     * Delete All by Pk.
     * 
     * @param pks PKs to delete
     * @return the int number of delete
     */
    int deleteAllByPk(Collection<? extends PK> pks);
    
    /**
     * Delete by property.
     * 
     * @param propertyName the property name
     * @param value the value
     * @return the int number of delete
     */
    int deleteByProperty(String propertyName, Object value);
    
    /**
     * Delete by property values.
     * 
     * @param propertyName the property name
     * @param values the values
     * @return the int number of delete
     */
    int deleteByProperty(String propertyName, Collection<? extends Object> values);

    /**
     * hibernate cache mgmt 
     * 
     * @param o the o
     */

    void evict(T o);

    /**
     * Evict object.
     * 
     * @param o the o
     */
    void evictObject(Object o);

    /**
     * Exist by criterion.
     * 
     * @param criterion the criterion
     * @return true, if exist by criterion
     */
    boolean existByCriterion(Criterion criterion);

    /**
     * Exist by id.
     * 
     * @param id the id
     * @return true, if exist by id
     */
    boolean existById(PK id);

    /**
     * Exist by property.
     * 
     * @param propertyName the property name
     * @param value the value
     * @return true, if exist by property
     */
    boolean existByProperty(String propertyName, Object value);

    /**
     * Find by criterion.
     * 
     * @param criterion the criterion
     * @return the T
     */
    T findByCriterion(Criterion criterion);
    
    /**
     * Find by id.
     * 
     * @param id the id
     * @return the T
     */
    T findById(PK id);

    /**
     * Find by property.
     * 
     * @param propertyName the property name
     * @param value the value
     * @return the T
     */
    T findByProperty(String propertyName, Object value);

    /**
     * List all.
     * 
     * @return the list< t>
     */
    List<T> listAll();

    /**
     * List all pk.
     * 
     * @return the list
     *         < p k>
     */
    List<PK> listAllPk();

    /**
     * List all with projection.
     * 
     * @param propertyName the property name
     * @return the list<?>
     */
    List<?> listAllWithProjection(String propertyName);

    /**
     * List by criterion.
     * 
     * @param criterion the criterion
     * @return the list< t>
     */
    List<T> listByCriterion(Criterion criterion);

    /**
     * List by criterion and order.
     * 
     * @param criterion the criterion
     * @param order the order
     * @return the list< t>
     */
    List<T> listByCriterionAndOrder(Criterion criterion, Order order);

    /**
     * List by property.
     * 
     * @param propertyName the property name
     * @param value the value
     * @return the list< t>
     */
    List<T> listByProperty(String propertyName, Object value);

    /**
     * Merge.
     * 
     * @param transientObject the transient object
     */
    void merge(T transientObject);
    
    /**
     * Refresh.
     * 
     * @param o the o
     */
    void refresh(T o);

    /**
     * Refresh object.
     * 
     * @param o the o
     */
    void refreshObject(Object o);

    /**
     * Update.
     * 
     * @param transientObject the transient object
     */
    void update(T transientObject);

    /**
     * Update all.
     * 
     * @param collection the collection
     */
    void updateAll(Collection<? extends T> collection);
}
