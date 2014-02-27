/**
 * 
 */
package jp.slm.business.bean.generic;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

/**
 * The Class GenericPkBean.
 * 
 * @param <PK> the generic type
 * @author rdurocher
 */
@SuppressWarnings({"serial"})
@MappedSuperclass
public abstract class GenericEmbeddedPkBean<PK extends Serializable> extends GenericPkBean<PK>
{
    /**
     * Gets the id.
     * 
     * @return the id
     */
    @EmbeddedId
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public PK getId()
    {
        return this.id;
    }
}
