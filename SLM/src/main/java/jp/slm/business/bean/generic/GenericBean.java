/**
 * 
 */
package jp.slm.business.bean.generic;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class GenericBean.
 * 
 * @author rdurocher
 */
@SuppressWarnings({"serial"})
public abstract class GenericBean implements Serializable
{
    
     /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /** 
     * (methode de remplacement)
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Transient
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * Play with reflection *.
     * 
     * @param type the type
     * @return the fields
     */

    @Transient
    public static Field[] getFields(Class<? extends GenericBean> type)
    {
        return type.getFields();
    }

    /**
     * Gets the field by name.
     * 
     * @param type the type
     * @param name the name
     * @return the field by name
     */
    @Transient
    public static Field getFieldByName(Class<? extends GenericBean> type, String name)
    {
        try
        {
            return type.getField(name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the constructors.
     * 
     * @param type the type
     * @return the constructors
     */
    @SuppressWarnings("unchecked")
    @Transient
    public static Constructor<? extends GenericBean>[] getConstructors(Class<? extends GenericBean> type)
    {
        try
        {
            return (Constructor<? extends GenericBean>[]) type.getConstructors();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the constructors by param class.
     * 
     * @param type the type
     * @param parameterTypes the parameter types
     * @return the constructors by param class
     */
    @Transient
    public static Constructor<? extends GenericBean> getConstructorsByParamClass(Class<? extends GenericBean> type,
            Class<?>... parameterTypes)
    {
        try
        {
            return type.getConstructor(parameterTypes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the constructors by param object.
     * 
     * @param type the type
     * @param parameterObj the parameter obj
     * @return the constructors by param object
     */
    @Transient
    public static Constructor<? extends GenericBean> getConstructorsByParamObject(Class<? extends GenericBean> type,
            Object... parameterObj)
    {
        if (parameterObj != null)
        {
            Class<?>[] parameterTypes = new Class<?>[parameterObj.length];
            int i = 0;
            for (Object o : Arrays.asList(parameterObj))
            {
                parameterTypes[i++] = o.getClass();
            }
            if (parameterTypes != null && parameterTypes.length == parameterObj.length)
            {
                return getConstructorsByParamClass(type, parameterTypes);
            }
        }
        return null;
    }

    /**
     * Gets the fields.
     * 
     * @return the fields
     */
    @Transient
    public Field[] getFields()
    {
        return getFields(this.getClass());
    }

    /**
     * Gets the field by name.
     * 
     * @param name the name
     * @return the field by name
     */
    @Transient
    public Field getFieldByName(String name)
    {
        return getFieldByName(this.getClass(), name);
    }

    /**
     * Gets the constructors.
     * 
     * @return the constructors
     */
    @Transient
    public Constructor<? extends GenericBean>[] getConstructors()
    {
        return getConstructors(this.getClass());
    }

    /**
     * Gets the constructors by param class.
     * 
     * @param parameterTypes the parameter types
     * @return the constructors by param class
     */
    @Transient
    public Constructor<? extends GenericBean> getConstructorsByParamClass(Class<?>... parameterTypes)
    {
        return getConstructorsByParamClass(this.getClass(), parameterTypes);
    }

    /**
     * Gets the constructors by param object.
     * 
     * @param parameterObj the parameter obj
     * @return the constructors by param object
     */
    @Transient
    public Constructor<? extends GenericBean> getConstructorsByParamObject(Object... parameterObj)
    {
        return getConstructorsByParamObject(this.getClass(), parameterObj);
    }

    /**
     * Gets the instance.
     * 
     * @param type the type
     * @param parameterObj the parameter obj
     * @return the instance
     */
    @Transient
    public static GenericBean getInstance(Class<? extends GenericBean> type, Object... parameterObj)
    {
        try
        {
            // Class.forName((new
            // RuntimeException()).getStackTrace()[1].getClassName())
            Constructor<?> c = getConstructorsByParamObject(type, parameterObj);
            if (c != null && c.isAccessible())
            {
                return (GenericBean) c.newInstance(parameterObj);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * New instance.
     * 
     * @param parameterObj the parameter obj
     * @return the generic bean
     */
    @Transient
    @SuppressWarnings("unchecked")
    public static GenericBean newInstance(Object... parameterObj)
    {
        try
        {
            Class<? extends GenericBean> type = (Class<? extends GenericBean>) Class.forName((new RuntimeException())
                    .getStackTrace()[1].getClassName());
            Constructor<? extends GenericBean> c = getConstructorsByParamObject(type, parameterObj);
            if (c != null && c.isAccessible())
            {
                return c.newInstance(parameterObj);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * New instance.
     * 
     * @return the generic bean
     */
    @Transient
    @SuppressWarnings("unchecked")
    public static GenericBean newInstance()
    {
        try
        {
            Class<? extends GenericBean> type = (Class<? extends GenericBean>) Class.forName((new RuntimeException())
                    .getStackTrace()[1].getClassName());
            type.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
