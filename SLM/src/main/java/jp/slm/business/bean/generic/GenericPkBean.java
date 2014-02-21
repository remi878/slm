/**
 * 
 */
package jp.slm.business.bean.generic;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class GenericPkBean.
 * 
 * @param <PK> the generic type
 * @author rdurocher
 */
@SuppressWarnings({"rawtypes", "serial"})
public abstract class GenericPkBean<PK extends Serializable> extends GenericBean implements Comparable<GenericPkBean<PK>>
{

    /** The id. */
    protected PK id;

    /**
     * Gets the id.
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public PK getId()
    {
        return this.id;
    }

    /**
     * Sets the id.
     * 
     * @param id the id
     */
    public void setId(PK id)
    {
        this.id = id;
    }

    /**
     * (methode de remplacement).
     * 
     * @return the int {@inheritDoc}
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * (methode de remplacement).
     * 
     * @param obj the obj
     * @return true, if equals {@inheritDoc}
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GenericPkBean other = (GenericPkBean) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compareTo(GenericPkBean gb)
    {
        int res = -1;

        if (gb != null)
        {
            if (gb.id == null && this.id == null)
            {
                res = 0;
            }
            else if (this.id == null)
            {
                res = 1;
            }
            else if (gb.id == null)
            {
                res = -1;
            }
            else if (this.id.getClass() == gb.id.getClass() && this.id instanceof Comparable)
            {
                Comparable a = (Comparable) this.id;
                Comparable b = (Comparable) gb.id;
                res = a.compareTo(b);
            }
            else
            {
                res = 0;
            }
        }
        return res;
    }
}
