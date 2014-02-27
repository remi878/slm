/**
 * 
 */
package jp.slm.business.bean.generic;

import javax.persistence.MappedSuperclass;


/**
 * The Class GenericLongIdBean.
 * 
 * @author rdurocher
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class GenericLongIdBean extends GenericSimplePkBean<Long>{

}
