/**
 * 
 */
package jp.slm.business.bean.generic;

import java.io.Serializable;

/**
 * The Class GenericPkBean.
 * 
 * @param <PK> the generic type
 * @author rdurocher
 */
@SuppressWarnings({"serial"})
public abstract class GenericEmbeddedPkBean<PK extends Serializable> extends GenericPkBean<PK>
{
    /**
     * Gets the id.
     * 
     * @return the id
     */
    public PK getId()
    {
        return this.id;
    }
}
