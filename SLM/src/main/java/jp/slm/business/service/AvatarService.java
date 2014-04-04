package jp.slm.business.service;

import jp.slm.business.bean.Avatar;
import jp.slm.business.service.generic.GenericLongIdBeanService;

import org.springframework.transaction.annotation.Transactional;


/**
 * The Interface AvatarService.
 * 
 */
@Transactional
public interface AvatarService extends GenericLongIdBeanService<Avatar> {

}
