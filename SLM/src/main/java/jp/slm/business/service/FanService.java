package jp.slm.business.service;

import org.springframework.transaction.annotation.Transactional;

import jp.slm.business.bean.Fan;
import jp.slm.business.service.generic.GenericLongIdBeanService;


/**
 * The Interface FanService.
 * 
 */
@Transactional
public interface FanService extends GenericLongIdBeanService<Fan> {

}
