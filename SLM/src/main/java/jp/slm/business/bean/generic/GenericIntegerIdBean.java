/**
 * 
 */
package jp.slm.business.bean.generic;

import javax.persistence.MappedSuperclass;


/**
 * The Class GenericIntegerIdBean.
 * 
 * @author rdurocher
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class GenericIntegerIdBean extends GenericSimplePkBean<Integer>{
    
}
