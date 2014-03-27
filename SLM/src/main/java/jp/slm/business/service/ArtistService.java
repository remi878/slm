package jp.slm.business.service;

import org.springframework.transaction.annotation.Transactional;

import jp.slm.business.bean.Artist;
import jp.slm.business.service.generic.GenericLongIdBeanService;


/**
 * The Interface ArtistService.
 * 
 */
@Transactional
public interface ArtistService extends GenericLongIdBeanService<Artist> {

}
